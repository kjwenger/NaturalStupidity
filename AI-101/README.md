# AI-101 Foundations of Artificial Intelligence

A one-semester, university-style introduction to AI, from classical search and logic to Transformers, LLMs and agents. It's laid out like a typical first AI module at a German university: 6 ECTS, a weekly lecture with an exercise sheet, programming labs and a written exam.

Published page: <https://claude.ai/artifact/9qJxokbxW5TphrHuqEJghF> (private; share it from the page's Share menu)

## What's in it

- **Module handbook entry**: credits, workload, prerequisites, learning outcomes
- **Syllabus**: 14 weeks in four parts: classical AI, uncertainty, machine learning, modern AI systems
- **14 lecture scripts**: learning objectives, numbered definitions, examples and theorems, summary, exercises with model solutions, readings
- **6 labs**: from A* to a RAG agent on the local LiteLLM router (see [labs/](labs/))
- **Sample exam**: 90 minutes, 90 points, with solutions
- **Glossary**: 149 terms with German equivalents, searchable, linked from the lectures

Several lectures and labs use the local fleet as worked examples: BosGameM5 (Strix Halo), LM Studio and the LiteLLM router on JoNAS (see [ORCHESTRATION.md](../ORCHESTRATION.md)).

## Layout

```
AI-101/
├── build.sh          assembles index.html; the UNITS list sets the page order
├── index.html        the built page (committed; this is what gets published)
├── src/
│   ├── shell/        head.html (styles, MathJax), nav.html, foot.html, app.js
│   ├── course/       overview, syllabus, assessment, readings
│   ├── lectures/     l01-what-is-ai.html … l14-safety-ethics-review.html
│   └── practice/     labs, exam, glossary (section shell)
├── data/
│   └── glossary.json the glossary terms, inlined into the page by build.sh
└── labs/             one folder per lab with its assignment
```

## Editing and building

1. Edit the unit under `src/`, or a term in `data/glossary.json`.
2. Run `./build.sh`. It rewrites `index.html`.
3. Open `index.html` in a browser to check, then commit `src/`, `data/` and `index.html` together.

Conventions in the lecture files:

- Each unit is one `<section class="unit" id="…" data-title="…">`. Lectures also have the class `lecture`, which gives them previous/next links and a "Mark as done" button.
- Maths is LaTeX for MathJax: `\( … \)` inline, `\[ … \]` displayed. Write `<` as `&lt;` inside maths.
- A glossary term is marked with `<dfn data-g="key">` where it's defined, or `<span class="g" data-g="key">` elsewhere. `key` must exist in `data/glossary.json`.
- Script environments: `<div class="env def|ex|thm"><span class="lbl">Definition 3.2 (…)</span>…</div>`; margin notes: `<p class="note"><b>Title.</b> …</p>`.

To add a lecture, create its file under `src/lectures/`, add it to `UNITS` in `build.sh`, and add a link to the sidebar in `src/shell/nav.html`.

## Glossary entry format

```json
{
  "key": "kv-cache",
  "term": "KV cache",
  "de": "KV-Cache",
  "lecture": 11,
  "definition": "Stored attention keys and values of all previous tokens, …"
}
```

`lecture` is the lecture that introduces the term, or `0` for general terms.
