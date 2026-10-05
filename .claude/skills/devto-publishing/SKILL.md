---
name: devto-publishing
description: Use when writing, formatting, or pushing a post to dev.to (DEV Community) - any .md destined for dev.to, its YAML front matter, tags, cover image or YouTube embeds, creating or updating a draft through the Forem API, or diagnosing a dev.to publish failure. Not specific to any post topic.
---

# dev.to Publishing

## Overview

Everything platform-specific about dev.to: front matter, tags, embeds, and pushing
a markdown file through the Forem API so nothing is copy-pasted into the web editor.

Topic-agnostic — it serves a DSA write-up, a UML explainer, or a tooling post
equally. The post's *content* shape belongs to whatever skill is producing it.

**Core principle: the markdown file is the source of truth.** Title, tags, cover
image and description all live in its front matter; the API call just transports
it. Never hand-edit a post in the dev.to editor and then re-push — the push wins
and the edit is lost.

## Quick reference

| Task | Where |
|---|---|
| Front matter fields, tag rules, title length | `references/frontmatter.md` |
| YouTube and other embeds, thumbnails, liquid tags | `references/embeds.md` |
| Auth setup, the publisher script, API failure modes | `references/gotchas.md` |

```bash
# validate only, no network write
python3 .claude/skills/devto-publishing/scripts/publish-devto.py <file.md> --dry-run

# create or update the draft
python3 .claude/skills/devto-publishing/scripts/publish-devto.py <file.md>

# take it live
python3 .claude/skills/devto-publishing/scripts/publish-devto.py <file.md> --publish
```

## Publishing Is The Author's Call

The script creates a **draft** by default. `--publish` makes it live.

**Never pass `--publish` on your own initiative.** A live post notifies followers
and is indexed within minutes — effectively irreversible. Create the draft, give
the author the URL, and let them decide. Pass `--publish` only when they ask for
that specific post to go live.

## Two Failure Modes That Look Identical

Both return **403**, and misreading them wastes a lot of time:

| Symptom | Cause |
|---|---|
| 403, **empty** body, `server: Varnish` | User-Agent / WAF block, not your key |
| 403 with a **JSON** body | key actually rejected |

dev.to's edge rejects the default `Python-urllib` User-Agent before the request
reaches the API. The script sends an explicit `User-Agent`; do not remove it.

Also: **`GET /articles/{id}` serves only published articles.** It 404s on a draft,
which looks like failure right after a successful create. Read drafts back from
`GET /articles/me/all`.

## Verify Writes, Do Not Trust Status Codes

Forem returns 200 while silently ignoring some changes. After any write, re-fetch
the article and assert the field you changed actually changed. The script does
this; keep it that way.

## Common Mistakes

- **More than 4 tags, or a tag with uppercase or punctuation.** dev.to rejects the
  post with a 422 that does not name the tag.
- **Editing in the dev.to web editor after pushing.** The next push overwrites it.
  Change the file instead.
- **Assuming liquid tags render everywhere.** `{% embed %}` works only on dev.to;
  anywhere else it is literal text. Pair it with plain markdown.
- **Leaving `canonical_url` empty on a cross-published post.** The copies then
  compete in search. Set it on whichever copy is not canonical.
- **Committing a key.** `.config/devto.config` is gitignored; only the `.example`
  is committed.
