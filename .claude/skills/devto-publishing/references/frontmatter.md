# dev.to front matter

Every field dev.to reads from the top of a markdown file, plus the title and
description rules that decide how the post looks in search results.

## Frontmatter

```yaml
---
title: "<hook that names the tension, not the problem>"
published: false
description: "<one sentence; appears in search results and social cards>"
tags: java, algorithms, interview, datastructures
cover_image: https://img.youtube.com/vi/<VIDEO_ID>/maxresdefault.jpg
canonical_url:
---
```

- `published: false` **always.** The author reviews before it goes live.
- **Title: 50-60 characters**, searchable term first. See Title Length below.
- **Maximum 4 tags**, lowercase alphanumeric only. No hyphens, no uppercase, no
  spaces — dev.to rejects them silently. Safe set: `java`, `algorithms`,
  `interview`, `datastructures`, `beginners`, `tutorial`, `programming`.
- `canonical_url` left empty. Flag to the user that it must be set if the post is
  cross-published, or the copies compete in search.
- Escape inner double quotes in `title` as `\"`.
- `cover_image` uses `maxresdefault.jpg` (1280x720). The README's thumbnail uses
  `0.jpg` — do not reuse that one here, it is 120x90 and renders blurry as a
  dev.to cover. If the video has no maxres thumbnail, fall back to `hqdefault.jpg`.
- Drop `cover_image` entirely when there is no video. Never point it at a guessed ID.

## Title length

**Target 50-60 characters.** Google truncates near 60 and the dev.to card
truncates too, so a longer title loses its ending — usually the hook.

**Lead with the searchable term, then the hook.** Readers search the problem name,
not the insight:

```
BAD   The Interview Question Where "Optimize It" Is a Trap (Your Social
      Network, Explained as a Real Interview)               -- 104 chars
GOOD  Your Social Network: When "Optimize It" Is a Trap     --  49 chars
```

Whatever the long version carried that the short one drops — the language, the
platform, the transcript framing — belongs in `description` and `tags`, which is
where search engines read it anyway. Hold `description` to about 150 characters
for the same truncation reason.


## Series

`series: <name>` groups posts into a dev.to series, which renders navigation
between the parts automatically. Use it when one topic is better as several
short posts than one long one — every part shares the series name verbatim.

## What the API does and does not override

Front matter in `body_markdown` takes precedence over the equivalent JSON
parameters, with one practical exception: `published` is widely reported to be
honored only as a JSON field. Set both to the same value. The publisher script
already does.
