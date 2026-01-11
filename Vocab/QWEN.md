# QWEN Instructional Context

## Directory Overview
This repository contains resources for **multilingual vocabulary and mnemonic generation**. The current structure is minimal:
```
Vocab/
├─ .qwen/                # Qwen‑Code configuration (model settings, etc.)
└─ Italiano/             # Placeholder for Italian word lists / mnemonics
```
The `Italiano` folder is intended to hold language‑specific data such as word lists, IPA transcriptions, definitions, and generated mnemonic files.

## Key Files
- **`.qwen/settings.json`** – Configuration used by Qwen‑Code (model provider, generation parameters, security settings). No code resides here; it merely tells the assistant which LLM to use.
- **`QWEN.md`** – This file. It provides a high‑level description for future interactions with the assistant, outlining project purpose and usage guidelines.

## Intended Usage
1. **Add language data**: Populate `Italiano/` (and later other language folders) with CSV/JSON files containing words, pronunciations, and meanings.
2. **Run mnemonic generation**: A separate script or CLI tool (to be implemented) will read those files, find sound‑similar pairs, and invoke an LLM to create memorable mnemonics.
3. **Iterate**: Extend the repository with additional languages, tests, and documentation as the project evolves.

## Development Conventions (TODO)
- Use UTF‑8 encoded CSV/JSON for word lists.
- Follow a consistent naming scheme, e.g., `italian_words.json`.
- Keep generated mnemonics in a subfolder like `Italiano/mnemonics/`.
- Add a `README.md` describing how to run the generation script once it exists.

*This file is a placeholder and should be expanded as the project grows.*
