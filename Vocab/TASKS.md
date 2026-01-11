# Task List

## Phase 1 – Italian Mnemonic Generator

| ID | Description | Status |
|----|-------------|--------|
| T1 | **Data ingestion**: Define CSV/JSON schema and implement loader (`load_words(lang)`). | pending |
| T2 | Add example Italian word list file (`Italiano/italian_words.json`) with a few entries for testing. | pending |
| T3 | Implement phonetic key function using Double Metaphone (language‑aware). | pending |
| T4 | Build similarity detection: index words, compute Levenshtein distance on IPA, output ranked `SimilarPair` list. | pending |
| T5 | Write caching layer for LLM responses (simple JSON file cache). | pending |
| T6 | Implement LLM wrapper (`generate_mnemonic(pair)`) that builds the prompt and calls the configured model endpoint. | pending |
| T7 | Create CLI entry point (`vocab` script) with sub‑commands `generate --lang it` and `query <word>`. | pending |
| T8 | Output generation: write `italian_mnemonics.json` and a human‑readable Markdown report. | pending |
| T9 | Add unit tests for loader, phonetic key, similarity detection, prompt builder, and cache logic. | pending |
| T10| Set up CI (GitHub Actions) to run tests on push. | pending |

## Phase 2 – Additional Languages & UI

| ID | Description | Status |
|----|-------------|--------|
| T11 | Add language‑specific phonetic modules for Spanish, French, German. | pending |
| T12 | Extend CLI to accept multiple languages and a `--all` flag. | pending |
| T13 | Implement simple Flask (or FastAPI) web UI to browse generated mnemonics. | pending |
| T14 | Export mnemonics to CSV/Anki deck format. | pending |
| T15 | Automate download of public word lists and IPA generation using `epitran`. | pending |

---
*Document last updated: 2026‑01‑11*