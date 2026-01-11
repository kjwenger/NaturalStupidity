# Multilingual Mnemonic Generator

## Overview
This repository provides a **multilingual mnemonic generation toolkit** that automatically discovers phonologically similar word pairs in any language and creates short, vivid mnemonics linking the two meanings. The initial implementation focuses on Italian and can be extended to other languages.

The workflow is:
1. **Provide a word list** (CSV/JSON) with orthographic form, IPA transcription, and definition.
2. **Detect sound‑similar pairs** using phonetic hashing (Double Metaphone) and Levenshtein distance on the IPA strings.
3. **Generate mnemonics** by prompting an LLM (OpenAI‑compatible API). Results are cached to avoid duplicate calls.
4. **Consume the output** via a CLI or, later, a simple web UI.

---
## Repository Layout
```
Vocab/
├─ .qwen/                # Qwen‑Code configuration (model settings)
├─ Italiano/             # Language‑specific data (e.g., italian_words.json)
├─ README.md            # This file
├─ SPEC.md              # Project specification
└─ TASKS.md             # Detailed task list
```

---
## Quick Start (Python implementation)
### Prerequisites
- **Python 3.10+**
- A virtual environment tool (`venv`, `pipenv` or `poetry`).
- An OpenAI‑compatible API key for the LLM (default endpoint is `http://localhost:1234/v1`). Set it in your environment:
```bash
export OPENAI_API_KEY=your_key_here
```
### Installation
```bash
# Clone the repository (if not already done)
git clone <repo‑url> /com.github/kjwenger/NaturalStupidity/Vocab
cd /com.github/kjwenger/NaturalStupidity/Vocab

# Create and activate a virtual environment
python -m venv .venv
source .venv/bin/activate   # on Windows: .venv\Scripts\activate

# Install dependencies
pip install -r requirements.txt  # (create this file when the project grows)
```
### Adding Italian data
Create a JSON file in `Italiano/` following the schema:
```json
[
  {
    "word": "scazzottata",
    "ipa": "skaˈd͡dzotta",
    "definition": "a slap, a blow"
  },
  {
    "word": "spazzatura",
    "ipa": "spaʣˈtura",
    "definition": "trash"
  }
]
```
Name it something like `italian_words.json`.

### Generate mnemonics
```bash
# Run the CLI (once implemented)
vocab generate --lang it
```
The command will:
- Load all word files in `Italiano/`.
- Find phonetic pairs.
- Call the LLM to create mnemonics.
- Write results to `Italiano/mnemonics/italian_mnemonics.json` and a Markdown report.

### Query a mnemonic
```bash
vocab query scazzottata
```
Outputs any mnemonic that involves the supplied word.

---
## Development
The project follows conventional Python layout:
- **src/** – core library (to be added).
- **tests/** – unit tests using `pytest`.
- **Makefile** – optional helper commands (`make test`, `make lint`).

### Running the test suite
```bash
pip install pytest
pytest
```

---
## Extending to Other Languages
1. Add a new folder (e.g., `Español/`) with its own word list.
2. Implement a language‑specific phonetic function if needed and register it in the loader.
3. The same CLI commands work for any supported language (`vocab generate --lang es`).

---
## License & Contributions
This is an open‑source project (MIT License). Feel free to fork, submit pull requests, or open issues for enhancements.

---
*Last updated: 2026‑01‑11*