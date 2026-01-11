# Project Specification

## Goal
Create a **multilingual mnemonic generator** that automatically discovers phonologically similar word pairs in any language and produces memorable, LLM‑generated mnemonics linking the two meanings.

## Scope (Phase 1 – Italian)
1. **Data ingestion**
   - Accept UTF‑8 CSV/JSON files containing:
     * `word` – orthographic form
     * `ipa`  – phonetic transcription (or generate via `epitran` if missing)
     * `definition`
   - Store these in a language‑specific directory (`Italiano/`).
2. **Similarity detection**
   - Compute a phonetic key using Double Metaphone (language aware).
   - Filter candidates with identical keys and a Levenshtein distance ≤ 2 on the IPA strings.
   - Output a ranked list of `SimilarPair` objects.
3. **Mnemonic generation**
   - For each pair, build an LLM prompt that:
     * Shows both words and definitions
     * Requests a short vivid mnemonic (≤ 120 characters)
   - Cache responses to avoid duplicate API calls.
4. **CLI interface**
   - `vocab generate --lang it` – builds all pairs & mnemonics, writes JSON/Markdown output.
   - `vocab query <word>` – returns any mnemonic containing the word.
5. **Output format**
   - `italian_mnemonics.json`: array of `{word1, word2, mnemonic}`.
   - Optional human‑readable Markdown report.

## Non‑functional Requirements
- **Python 3.10+**, pure‑Python dependencies (no compiled extensions).
- Configurable LLM endpoint via environment variables (`OPENAI_API_KEY`, `BASE_URL`).
- Robust error handling; fall back to grapheme distance when IPA missing.
- Unit tests covering phonetic indexing, pair extraction, prompt generation, and caching.

## Future Extensions (Phase 2+)
- Add support for additional languages (Spanish, French, German, …) by providing language‑specific phonetic functions.
- Provide a simple web UI to browse mnemonics.
- Export to CSV/Anki deck format.
- Automatic download of word lists from Wiktionary or other public corpora.

---
*Document last updated: 2026‑01‑11*