#!/usr/bin/env bash
# AI-101/build.sh — assemble index.html from src/ and data/.
#
# The page order is the UNITS list below, not the file names: to add or move
# a lecture, edit this list. The glossary is inlined from data/glossary.json.
#
# Usage: ./build.sh            (writes index.html next to this script)

set -euo pipefail
cd "$(dirname "$0")"

UNITS=(
  course/overview.html
  course/syllabus.html
  course/assessment.html
  course/readings.html
  lectures/l01-what-is-ai.html
  lectures/l02-search.html
  lectures/l03-games-constraints.html
  lectures/l04-logic-knowledge.html
  lectures/l05-probability.html
  lectures/l06-machine-learning.html
  lectures/l07-optimisation.html
  lectures/l08-neural-networks.html
  lectures/l09-deep-architectures.html
  lectures/l10-attention-transformers.html
  lectures/l11-large-language-models.html
  lectures/l12-reinforcement-learning.html
  lectures/l13-agents-tools-rag.html
  lectures/l14-safety-ethics-review.html
  practice/labs.html
  practice/exam.html
  practice/glossary.html
)

# Fail early on a broken glossary rather than shipping a page whose script dies.
python3 -c 'import json,sys; json.load(open(sys.argv[1]))' data/glossary.json

{
  cat src/shell/head.html
  cat src/shell/nav.html
  for u in "${UNITS[@]}"; do
    echo
    cat "src/$u"
  done
  echo
  cat src/shell/foot.html
  printf '<script>\nconst GLOSSARY = '
  cat data/glossary.json
  printf ';\n</script>\n<script>\n'
  cat src/shell/app.js
  printf '</script>\n'
} > index.html.tmp
mv index.html.tmp index.html

echo "Built index.html ($(wc -c < index.html) bytes, ${#UNITS[@]} units)"
