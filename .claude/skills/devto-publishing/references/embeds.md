# dev.to embeds, thumbnails, and anchors

Liquid tags, image sizing, and heading anchors — the markdown mechanics specific
to how dev.to renders a post, and what happens to them anywhere else.

## Linking the YouTube video

The video is the reason the post exists — it drives watch time. It appears in
**three** places, and all three are REQUIRED when a video exists.

The author supplies the link as `https://youtu.be/<VIDEO_ID>` (same form as the
README's `> Video description:` line). Extract `<VIDEO_ID>` from it — the segment
after the last `/`, before any `?`.

### 1. Cover image (frontmatter)

```yaml
cover_image: https://img.youtube.com/vi/<VIDEO_ID>/maxresdefault.jpg
```

### 2. Inline player, immediately after the spoiler paragraph

The liquid embed alone — **no thumbnail here.** One line of context first so it
does not sit bare:

```markdown
Prefer to watch the walkthrough? It covers the same ground as this transcript:

{% embed https://www.youtube.com/watch?v=<VIDEO_ID> %}
```

A thumbnail directly above the player shows the same frame twice in a row, and on
dev.to the cover image makes that three times before the post even starts. The
thumbnail's job is the footer; up here the player is enough.

The liquid tag needs the **full `youtube.com/watch?v=` URL**, not the `youtu.be`
short form.

Place it **after** the spoiler paragraph, not above it. The spoiler is what search
engines and language models extract; an embed above it pushes that text down.

**Alternative:** `{% youtube <VIDEO_ID> %}` is equivalent and additionally accepts
`start=` and `end=` in seconds — `{% youtube <VIDEO_ID> start=90 %}` — useful for
deep-linking a beat to the moment the video explains it. **Timestamps must come
from the author.** Never estimate where a beat falls in a video you have not watched.

### 3. Footer: clickable thumbnail

Plain markdown, so it survives outside dev.to. A linked thumbnail rather than a
bare URL — it reads as a call to action and gives the footer a visual anchor:

```markdown
Video walkthrough:

[![Watch the walkthrough on YouTube](https://img.youtube.com/vi/<VIDEO_ID>/maxresdefault.jpg)](https://youtu.be/<VIDEO_ID>)

**[youtu.be/<VIDEO_ID>](https://youtu.be/<VIDEO_ID>)**
```

Keep the plain text link beneath the image. If images are blocked or the
thumbnail 404s, the link still works.

Thumbnail size matters here. `maxresdefault.jpg` is 1280x720 and renders at full
content width on both dev.to and GitHub. The README's `0.jpg` is 120x90 and
renders as a thumbnail the size of a postage stamp — do not reuse it. Alt text is
required: it is what screen readers announce and what search engines index.

**`maxresdefault.jpg` does not exist for every video** — only for ones uploaded
at 720p or above, and YouTube 404s rather than substituting. Check before using it:

```bash
curl -s -o /dev/null -w "%{http_code}\n" https://img.youtube.com/vi/<VIDEO_ID>/maxresdefault.jpg
```

Anything other than 200 → fall back to `hqdefault.jpg` (480x360), which always exists.

### Liquid tags only render on dev.to

`DEVTO.md` also lives in the repo, where GitHub renders `{% embed ... %}` as
literal text. The footer thumbnail covers that gap: the inline embed gives dev.to
readers a real player, and the footer thumbnail gives every other reader something
visual and clickable. Keep both — they are the same video in different zones, for
different renderers. Do not stack them together to look tidy.

### No video yet

Omit all three. Write the post, then tell the user which three places need the
link once the video is up. Do not invent an ID, and do not leave `<VIDEO_ID>`
placeholders in the file — a published post with a broken embed is worse than one
with no embed.

## Jump links for long posts

Over roughly 8 minutes' reading time, add jump links after the video embed. Group
them by phase instead of listing every heading flat:

```markdown
**Jump to —**

*Setup:* [the problem as asked](#...) · [draw it first](#...)
*The pivot:* [brute force](#...) · [why you cannot do better](#...)
*The code:* [writing it](#...) · [the ordering trap](#...)
*Wrap-up:* [what was graded](#...) · [FAQ](#...)
```

Anchor slugs: lowercase the heading, strip `*`, `_` and backticks, replace every
run of non-alphanumeric characters with one hyphen, trim leading/trailing hyphens.
So `Minute 12: The correct answer to "can you do better?" is no` becomes
`minute-12-the-correct-answer-to-can-you-do-better-is-no`.

Verified against dev.to's rendered HTML: it emits `<a name="slug">` inside the
heading rather than `id=` on it, but `#slug` links resolve either way, and GitHub
derives the same slug from the same heading.


## Other embeds

`{% embed <URL> %}` also covers CodePen, CodeSandbox, GitHub gists and repos,
Replit, StackBlitz, Spotify, Twitch, Vimeo, Wikipedia and Stack Overflow, among
others. `{% link <dev.to URL> %}` renders a rich card for another DEV post, and
`{% user <username> %}` renders a profile card.

All of them share the same caveat: they render on dev.to only. Anywhere else the
file is read, they are literal text.
