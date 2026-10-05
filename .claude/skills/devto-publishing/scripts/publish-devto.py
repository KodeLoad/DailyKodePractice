#!/usr/bin/env python3
"""Publish a DEVTO.md file to dev.to via the Forem API.

Creates a DRAFT by default. Going live requires an explicit --publish flag.

  python3 publish-devto.py GeeksForGeeks/<Dir>/DEVTO.md                 # draft
  python3 publish-devto.py GeeksForGeeks/<Dir>/DEVTO.md --dry-run       # no network write
  python3 publish-devto.py GeeksForGeeks/<Dir>/DEVTO.md --publish       # LIVE
  python3 publish-devto.py GeeksForGeeks/<Dir>/DEVTO.md --id 1234567    # force-update

Auth, in order of precedence:
  1. $DEVTO_API_KEY
  2. the file named by $DEVTO_API_KEY_FILE
  3. .config/devto.config in this repo   (copy .config/devto.config.example)
  4. ~/.config/devto/config              (same format, outside the repo)

Never pass the key as a command-line argument - it would land in shell history
and in the process list.

Key source: https://dev.to/settings/extensions -> "DEV Community API Keys".
"""

import argparse
import json
import os
import re
import sys
import urllib.error
import urllib.request

API = "https://dev.to/api"

# dev.to sits behind Varnish, which rejects the default Python-urllib User-Agent
# with an empty-bodied 403 before the request reaches the API. Any real UA works.
# Do not remove this header - a bad key returns 403 too, so the symptoms are
# identical and the cause is extremely easy to misdiagnose as "invalid API key".
USER_AGENT = "DailyKodePractice-devto-publisher/1.0 (+https://github.com/KodeLoad/DailyKodePractice)"
CONFIG_REL = ".config/devto.config"        # repo-local, gitignored
STATE_FILE = ".config/devto-ids.json"      # sidecar: path -> {id, url, title}


# --------------------------------------------------------------------------- io

def die(msg, *extra):
    print(f"error: {msg}", file=sys.stderr)
    for e in extra:
        print(f"       {e}", file=sys.stderr)
    sys.exit(1)


def repo_root():
    d = os.path.abspath(os.curdir)
    while d != "/":
        if os.path.isdir(os.path.join(d, ".git")):
            return d
        d = os.path.dirname(d)
    return os.path.abspath(os.curdir)


PLACEHOLDER_MARKERS = ("your_", "yourkey", "paste", "xxx", "<", "replace", "example")


def parse_config(path):
    """Read KEY=value pairs from a .env-style config file.

    Tolerates `export KEY=value`, # comments, blank lines, and quoted values.
    """
    out = {}
    with open(path) as f:
        for line in f:
            line = line.strip()
            if not line or line.startswith("#"):
                continue
            if line.startswith("export "):
                line = line[len("export "):].strip()
            if "=" not in line:
                continue
            k, v = line.split("=", 1)
            v = v.split(" #")[0].strip()
            if len(v) >= 2 and v[0] == v[-1] and v[0] in "\"'":
                v = v[1:-1]
            out[k.strip()] = v
    return out


def load_key():
    """Resolve the API key. Order is most-specific to most-convenient."""
    root = repo_root()
    home_cfg = os.path.expanduser("~/.config/devto/config")

    # 1. environment variable
    k = os.environ.get("DEVTO_API_KEY", "").strip()
    if k:
        return check_key(k, "$DEVTO_API_KEY")

    # 2. file named by $DEVTO_API_KEY_FILE (bare key)
    envfile = os.environ.get("DEVTO_API_KEY_FILE")
    if envfile and os.path.isfile(envfile):
        k = open(envfile).read().strip()
        if k:
            return check_key(k, f"$DEVTO_API_KEY_FILE ({envfile})")

    # 3./4. .env-style config: repo-local .config/ first, then home
    for cfg, label in ((os.path.join(root, CONFIG_REL), CONFIG_REL),
                       (home_cfg, "~/.config/devto/config")):
        if os.path.isfile(cfg):
            k = parse_config(cfg).get("DEVTO_API_KEY", "").strip()
            if k:
                return check_key(k, label)

    die("no dev.to API key found.",
        "",
        "Get one: https://dev.to/settings/extensions -> scroll to bottom ->",
        "         'DEV Community API Keys' -> name it -> Generate API Key",
        "",
        "Then store it in one of these (first match wins):",
        f"  cp {CONFIG_REL}.example {CONFIG_REL}    <- the default; then edit it",
        "  export DEVTO_API_KEY=<key>",
        f"  {home_cfg}   (same format, outside the repo)",
        "",
        f"{CONFIG_REL} is gitignored. Never commit a key.")


def check_key(key, source):
    """Catch an unedited placeholder before spending a round trip on a 403."""
    low = key.lower()
    if any(m in low for m in PLACEHOLDER_MARKERS) or len(key) < 8:
        die(f"the key from {source} looks like an unedited placeholder: {key[:24]!r}",
            "Replace it with a real key from https://dev.to/settings/extensions.")
    return key, source


def request(method, path, key, payload=None):
    url = f"{API}{path}"
    data = json.dumps(payload).encode() if payload is not None else None
    req = urllib.request.Request(url, data=data, method=method)
    req.add_header("api-key", key)
    req.add_header("Accept", "application/vnd.forem.api-v1+json")
    req.add_header("User-Agent", USER_AGENT)
    if data:
        req.add_header("Content-Type", "application/json")
    try:
        with urllib.request.urlopen(req, timeout=60) as r:
            body = r.read().decode()
            return r.status, (json.loads(body) if body.strip() else {})
    except urllib.error.HTTPError as e:
        body = e.read().decode(errors="replace")
        try:
            detail = json.loads(body)
        except json.JSONDecodeError:
            detail = body[:500]
        hint = {
            401: "API key rejected. Regenerate it at https://dev.to/settings/extensions.",
            403: "Either the API key is wrong, or the request was blocked upstream. "
                 "An EMPTY response body with server=Varnish means a User-Agent/WAF "
                 "block, not a key problem; a JSON body means the key was rejected - "
                 "check it at https://dev.to/settings/extensions.",
            422: "dev.to rejected the article. Most often: >4 tags, a tag with "
                 "uppercase/punctuation, or a title that is empty or duplicated.",
            429: "Rate limited. dev.to throttles article writes - wait ~30s and retry.",
        }.get(e.code, "")
        die(f"HTTP {e.code} on {method} {path}", json.dumps(detail)[:500], hint)
    except urllib.error.URLError as e:
        die(f"could not reach dev.to: {e.reason}")


# ---------------------------------------------------------------- front matter

def split_front_matter(text):
    m = re.match(r"^---\n(.*?)\n---\n(.*)$", text, re.S)
    if not m:
        die("file has no YAML front matter.",
            "A DEVTO.md must start with a --- block. See references/devto-format.md.")
    return m.group(1), m.group(2)


def fm_get(fm, field):
    m = re.search(rf"^{field}:\s*(.*)$", fm, re.M)
    if not m:
        return None
    v = m.group(1).strip()
    if len(v) >= 2 and v[0] == v[-1] and v[0] in "\"'":
        v = v[1:-1].replace('\\"', '"')
    return v or None


def set_published(text, value):
    """Rewrite the front matter's published: line in the body we submit.

    The official docs say front matter wins over JSON params; a widely reported
    behavior says `published` in front matter is ignored and only the JSON field
    counts. Setting both to the same value is correct under either rule.
    """
    fm, body = split_front_matter(text)
    literal = "true" if value else "false"
    if re.search(r"^published:", fm, re.M):
        fm = re.sub(r"^published:.*$", f"published: {literal}", fm, count=1, flags=re.M)
    else:
        fm = f"published: {literal}\n{fm}"
    return f"---\n{fm}\n---\n{body}"


def validate(fm, path):
    problems = []

    title = fm_get(fm, "title")
    if not title:
        problems.append("no `title:` in front matter")

    raw_tags = fm_get(fm, "tags") or ""
    tags = [t.strip() for t in raw_tags.split(",") if t.strip()]
    if len(tags) > 4:
        problems.append(f"{len(tags)} tags; dev.to allows at most 4 ({', '.join(tags)})")
    bad = [t for t in tags if not re.fullmatch(r"[a-z0-9]+", t)]
    if bad:
        problems.append(f"tags must be lowercase alphanumeric only; offending: {', '.join(bad)}")

    body = open(path, encoding="utf-8").read()
    if "<VIDEO_ID>" in body:
        problems.append("file still contains a <VIDEO_ID> placeholder")

    if problems:
        die("front matter is not publishable:", *problems)

    return title, tags


# --------------------------------------------------------------------- sidecar

def state_path():
    return os.path.join(repo_root(), STATE_FILE)


def load_state():
    p = state_path()
    if os.path.isfile(p):
        try:
            with open(p) as f:
                return json.load(f)
        except json.JSONDecodeError:
            return {}
    return {}


def save_state(state):
    os.makedirs(os.path.dirname(state_path()), exist_ok=True)
    with open(state_path(), "w") as f:
        json.dump(state, f, indent=2, sort_keys=True)
        f.write("\n")


def rel(path):
    try:
        return os.path.relpath(os.path.abspath(path), repo_root())
    except ValueError:
        return os.path.abspath(path)


# ------------------------------------------------------------------------ main

def find_existing(key, title):
    """Match an existing article by exact title across drafts and published."""
    _, arts = request("GET", "/articles/me/all?per_page=1000", key)
    for a in arts if isinstance(arts, list) else []:
        if (a.get("title") or "").strip() == title.strip():
            return a
    return None


def fetch_own(art_id, key, published):
    """Read back an article this script just wrote.

    GET /articles/{id} serves only PUBLISHED articles - it returns 404 for a
    draft, which looks alarming right after a successful create. Drafts have to
    be read from the authenticated listing instead.
    """
    if published:
        _, a = request("GET", f"/articles/{art_id}", key)
        return a

    _, arts = request("GET", "/articles/me/all?per_page=1000", key)
    for a in arts if isinstance(arts, list) else []:
        if a.get("id") == art_id:
            return a
    die(f"wrote article #{art_id} but could not read it back from /articles/me/all.",
        "The write probably succeeded - check https://dev.to/dashboard before re-running,",
        "so you do not create a duplicate.")


def main():
    ap = argparse.ArgumentParser(description="Publish a DEVTO.md to dev.to.")
    ap.add_argument("file", help="path to DEVTO.md")
    ap.add_argument("--publish", action="store_true",
                    help="publish LIVE (default: create/update as a draft)")
    ap.add_argument("--id", type=int, help="update this article id directly")
    ap.add_argument("--dry-run", action="store_true",
                    help="validate and show the plan; makes no write request")
    args = ap.parse_args()

    if not os.path.isfile(args.file):
        die(f"no such file: {args.file}")

    text = open(args.file, encoding="utf-8").read()
    fm, _ = split_front_matter(text)
    title, tags = validate(fm, args.file)

    body = set_published(text, args.publish)
    state_key = rel(args.file)

    print(f"file      : {state_key}")
    print(f"title     : {title}")
    print(f"tags      : {', '.join(tags) or '(none)'}")
    print(f"state     : {'PUBLISHED (live, visible to followers)' if args.publish else 'draft'}")
    print(f"body      : {len(body)} chars")

    if args.dry_run:
        # Resolve the target without writing, if a key happens to be available.
        target = None
        if args.id:
            target = f"PUT /articles/{args.id} (--id)"
        elif state_key in load_state():
            target = f"PUT /articles/{load_state()[state_key]['id']} (from {STATE_FILE})"
        else:
            target = "lookup by title, then PUT if found else POST /articles"
        print(f"would do  : {target}")
        print("\ndry run - nothing sent.")
        return

    key, key_src = load_key()
    print(f"auth      : {key_src}")

    # Resolve create vs update: explicit id > sidecar > title match > create.
    state = load_state()
    art_id = args.id or (state.get(state_key) or {}).get("id")
    if not art_id:
        found = find_existing(key, title)
        if found:
            art_id = found.get("id")
            print(f"matched   : existing article #{art_id} by title")

    payload = {"article": {"body_markdown": body, "published": bool(args.publish)}}

    if art_id:
        print(f"action    : PUT /articles/{art_id}")
        _, res = request("PUT", f"/articles/{art_id}", key, payload)
    else:
        print("action    : POST /articles")
        _, res = request("POST", "/articles", key, payload)
        art_id = res.get("id")

    # Gotcha: dev.to returns 200 while silently ignoring some changes.
    # Read the article back and assert, rather than trusting the status code.
    check = fetch_own(art_id, key, args.publish)
    got_title = (check.get("title") or "").strip()
    got_pub = bool(check.get("published"))
    url = check.get("url") or res.get("url") or ""

    print()
    print(f"id        : {art_id}")
    print(f"url       : {url}")
    print(f"verified  : title {'OK' if got_title == title.strip() else f'MISMATCH ({got_title!r})'}"
          f" | published={got_pub} {'OK' if got_pub == bool(args.publish) else 'MISMATCH'}")

    if got_title != title.strip() or got_pub != bool(args.publish):
        die("dev.to accepted the request but the article does not match what was sent.",
            "This is a known Forem behavior - the write was partially ignored.",
            "Re-run, or edit the post in the dev.to web editor.")

    state[state_key] = {"id": art_id, "url": url, "title": title}
    save_state(state)
    print(f"recorded  : {STATE_FILE} (re-runs update #{art_id} instead of duplicating)")

    if not args.publish:
        print("\nDraft created. Review it, then publish with --publish (or in the dev.to editor).")


if __name__ == "__main__":
    main()
