#!/usr/bin/env bash
# Fetch a GeeksForGeeks problem as readable text.
#
# The public problem page (geeksforgeeks.org/problems/<slug>/1) is rendered
# client-side: fetching it returns only nav chrome, no problem statement.
# This hits the practice API instead, which returns structured JSON.
#
# Usage: bash fetch-problem.sh <slug>
#        bash fetch-problem.sh https://www.geeksforgeeks.org/problems/<slug>/1
#        bash fetch-problem.sh <slug> --raw     # full JSON, for fields not printed below

set -euo pipefail

if [ $# -lt 1 ]; then
  echo "usage: bash fetch-problem.sh <slug|problem-url> [--raw]" >&2
  exit 2
fi

# Accept a full problem URL and reduce it to the slug.
slug=$(printf '%s' "$1" | sed -E 's#^https?://[^/]*/problems/##; s#/[0-9]*/?$##; s#/$##')
shift || true

json=$(curl -sS --max-time 30 "https://practiceapi.geeksforgeeks.org/api/vr/problems/${slug}/")

if [ "${1:-}" = "--raw" ]; then
  printf '%s' "$json" | python3 -m json.tool
  exit 0
fi

printf '%s' "$json" | SLUG="$slug" python3 -c '
import sys, json, re, html, os

raw = sys.stdin.read()
try:
    d = json.loads(raw)
except json.JSONDecodeError:
    sys.exit("Could not parse API response. Slug likely wrong: " + os.environ["SLUG"]
             + "\nCheck it against the problem URL, or retry with --raw.")

if isinstance(d, dict) and d.get("error"):
    e = d["error"]
    sys.exit("API error %s: %s\nSlug used: %s\nTake the slug from the problem URL exactly: "
             "geeksforgeeks.org/problems/<SLUG>/1" % (
                 e.get("code", "?"), e.get("message", "?"), os.environ["SLUG"]))

r = d.get("results", d)

if not r.get("problem_name"):
    sys.exit("API returned no problem_name for slug %s - treat this as a failed fetch, "
             "not as a problem with missing data. Re-check the slug." % os.environ["SLUG"])

def text(frag):
    """Strip HTML to readable text, preserving block boundaries."""
    if not frag:
        return ""
    s = str(frag)
    s = re.sub(r"(?is)<(script|style)\b.*?</\1>", "", s)
    s = re.sub(r"(?i)<br\s*/?>", "\n", s)
    s = re.sub(r"(?i)</(p|div|li|tr|h[1-6]|pre)>", "\n", s)
    s = re.sub(r"(?i)<li[^>]*>", "  - ", s)
    s = re.sub(r"<[^>]+>", "", s)
    s = html.unescape(s)
    s = re.sub(r"[ \t]+\n", "\n", s)
    s = re.sub(r"\n{3,}", "\n\n", s)
    return s.strip()

tags = r.get("tags") or {}
company = tags.get("company_tags") or []
topic = tags.get("topic_tags") or []

print("=" * 72)
print("NAME       :", r.get("problem_name", "?"))
print("SLUG       :", os.environ["SLUG"])
print("DIFFICULTY :", r.get("difficulty", "?"), " (API value - do not guess, do not restate from memory)")
print("ACCURACY   :", r.get("accuracy", "?"))
print("TOPIC TAGS :", ", ".join(topic) if topic else "(none returned by API)")
print("COMPANIES  :", ", ".join(company) if company else "(NONE returned by API -> any Companies: line you write is convention, not sourced. Tell the user.)")
print("=" * 72)
print()
print("--- PROBLEM STATEMENT ---")
print(text(r.get("problem_question")))

cons = text(r.get("constraints_display"))
if cons:
    print()
    print("--- CONSTRAINTS ---")
    print(cons)

v = text(r.get("custom_input_format"))
if v:
    print(); print("--- CUSTOM INPUT FORMAT ---"); print(v)

tc = r.get("test_cases")
if tc:
    print(); print("--- SAMPLE TEST CASES ---"); print(text(tc)[:2000])

urls = [a.get("article_url") or a.get("url") for a in (r.get("article_list") or [])
        if isinstance(a, dict) and (a.get("article_url") or a.get("url"))]
if urls:
    print(); print("--- GFG ARTICLES ---")
    for u in urls[:5]:
        print("  -", u)

print()
print("NOTE: The API returns no expected-time/space-complexity fields. If you write a")
print("      Complexity line in the README, it is your own analysis - derive it from")
print("      Solution.java and say so to the user.")
' 