# Auth, the publisher script, and API failure modes

`scripts/publish-devto.py` pushes any markdown file through the Forem API, so the
file is the source of truth and nothing is copy-pasted into the web editor.

## One-time setup

### Getting the key

1. Log in to https://dev.to
2. Open **https://dev.to/settings/extensions**
   (or: avatar → Settings → **Extensions** in the left sidebar)
3. Scroll to the **bottom** of that page, to **"DEV Community API Keys"**
4. Enter a description, e.g. `DailyKodePractice CLI`
5. Click **Generate API Key**, then copy the key from the list

The key stays visible in that list, so it can be copied again later. If it leaks,
delete it there and generate a new one.

### Storing the key

```bash
cp .config/devto.config.example .config/devto.config    # then edit it
```

`.config/devto.config` is a `.env`-style file holding `DEVTO_API_KEY=...`. It is
gitignored; `.config/devto.config.example` holds only placeholders and is committed.

The script resolves the key in this order, first match wins:

| # | Source | Notes |
|---|---|---|
| 1 | `$DEVTO_API_KEY` | environment variable; good for CI |
| 2 | `$DEVTO_API_KEY_FILE` | path to a file containing just the key |
| 3 | `<repo>/.config/devto.config` | `.env` style — **the default** |
| 4 | `~/.config/devto/config` | same format, outside the git tree |

Option 4 is the safest — no secret ever sits inside a working tree, gitignored or not:

```bash
mkdir -p ~/.config/devto
cp .config/devto.config.example ~/.config/devto/config
chmod 600 ~/.config/devto/config
```

### What lives in `.config/`

| Path | Committed? | What it is |
|---|---|---|
| `.config/devto.config.example` | yes | template, placeholders only |
| `.config/devto.config` | **no** | your real key |
| `.config/devto-ids.json` | **no** | article ids, so re-runs update instead of duplicating |

The parser tolerates `export KEY=value`, quoted values, `#` comments, and blank
lines, so the same file works when sourced by a shell.

### Two safety properties

**The key is never a command-line argument.** It would otherwise land in shell
history and be visible in the process list to any other user on the machine. The
script reads it only from the environment or a file.

**An unedited placeholder is rejected before any network call.** Copying
`.config/devto.config.example` without editing it produces a clear error naming it,
rather than a confusing `403` from dev.to.

## Usage

```bash
# validate and show the plan, no network write
python3 .claude/skills/devto-publishing/scripts/publish-devto.py \
    <path/to/post.md> --dry-run

# create or update the draft
python3 .claude/skills/devto-publishing/scripts/publish-devto.py \
    <path/to/post.md>

# take it live
python3 .claude/skills/devto-publishing/scripts/publish-devto.py \
    <path/to/post.md> --publish
```

`--id <N>` targets a specific article when the title has changed since the last push.

## Draft by default

Omitting `--publish` creates or updates an unpublished draft, visible only to the
author at its dev.to URL. This is the safe default and matches the `published: false`
that `references/devto-format.md` requires in front matter.

**An agent never passes `--publish` unprompted.** Publishing notifies followers and
is indexed quickly — it is the author's decision, per post.

## Re-runs do not duplicate

After a successful push the article id is recorded in `.config/devto-ids.json`
(gitignored) keyed by file path, so the next run updates that article instead of
creating a second one. Resolution order:

1. `--id` if given
2. `.config/devto-ids.json` entry for this path
3. exact title match against `GET /articles/me/all`
4. otherwise create

The title match is the fallback that covers a deleted state file. It fails only if
the title changed *and* the state file is gone — then pass `--id`.

## Pre-flight validation

The script refuses to send a file that dev.to would reject or that is unfinished:

- missing YAML front matter
- missing `title:`
- more than 4 tags, or a tag that is not lowercase alphanumeric
- a leftover `<VIDEO_ID>` placeholder

## Two Forem behaviors the script works around

**`published` is handled twice, deliberately.** The official docs say front matter
wins over JSON params; a widely reported behavior says `published` in front matter
is ignored and only the JSON field counts. The script sets both to the same value —
correct under either rule. It rewrites the front matter only in the payload it
sends; the file on disk is never modified.

**dev.to returns 200 while silently ignoring changes.** So the script re-fetches the
article after every write and asserts the title and published state actually match
what was sent, failing loudly on a mismatch. Do not replace this with a status-code
check.

**dev.to is behind Varnish, which blocks unknown User-Agents.** The default
`Python-urllib/3.x` UA gets an **empty-bodied 403** before the request reaches the
API — identical in appearance to a rejected key, and very easy to misdiagnose as
"my API key has no permission". The script sends an explicit `User-Agent`; do not
remove it. Telling the two apart:

| Symptom | Cause |
|---|---|
| 403, **empty** body, `server: Varnish` | User-Agent / WAF block |
| 403 or 401 with a **JSON** body | key actually rejected |

**`GET /articles/{id}` serves only published articles.** It returns 404 for a
draft, which looks like failure immediately after a successful create. The
read-back therefore uses `GET /articles/me/all` when the target is a draft, and
`GET /articles/{id}` only when publishing.

Also note: a rejected API key returns **403**, not 401.

## What the script does not do

- **Comments** — the Forem API is read-only for comments. No automation possible.
- **Deleting a post** — not exposed. Use the dev.to web editor.
- **`canonical_url`** — left blank by the format. Set it in the front matter before
  pushing if the post is cross-published, or the copies compete in search.
