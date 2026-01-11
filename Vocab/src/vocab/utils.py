"""Utility functions for loading data and computing phonetic similarity.

The implementation is deliberately lightweight and uses only the Python standard library plus a few optional
dependencies that can be added later (e.g., `metaphone` for Double Metaphone).  If the optional package is not
available we fall back to a very simple fallback based on lower‑casing the word.
"""

import json
import os
from pathlib import Path
from typing import List, Dict

from .models import WordInfo

# ---------------------------------------------------------------------------
# Data loading
# ---------------------------------------------------------------------------

def load_word_file(path: Path) -> List[WordInfo]:
    """Load a JSON file containing a list of word dictionaries.

    Expected schema for each entry::
        {
            "word": "string",
            "ipa": "string",   # optional – if missing we will try to generate later
            "definition": "string"
        }
    """
    with path.open(encoding="utf-8") as f:
        raw = json.load(f)
    words: List[WordInfo] = []
    for entry in raw:
        word = entry.get("word", "").strip()
        ipa = entry.get("ipa", "").strip()
        definition = entry.get("definition", "").strip()
        if not word:
            continue
        words.append(WordInfo(word=word, ipa=ipa, definition=definition))
    return words


def load_language_data(lang_dir: Path) -> List[WordInfo]:
    """Collect all JSON files in *lang_dir* and merge their contents.

    The function ignores hidden files and non‑JSON extensions.
    """
    all_words: List[WordInfo] = []
    for file_path in lang_dir.glob("*.json"):
        if file_path.name.startswith('.'):
            continue
        all_words.extend(load_word_file(file_path))
    return all_words

# ---------------------------------------------------------------------------
# Phonetic helpers (optional Double Metaphone)
# ---------------------------------------------------------------------------

try:
    # The `metaphone` package provides a DoubleMetaphone implementation.
    from metaphone import doublemetaphone  # type: ignore
except Exception:  # pragma: no cover – optional dependency may be missing
    doublemetaphone = None  # type: ignore


def phonetic_key(word: str) -> str:
    """Return a language‑agnostic phonetic key.

    If the `metaphone` library is available we use Double Metaphone and return the first element;
    otherwise we fall back to the lower‑cased word itself.  This ensures the function always works,
    albeit with reduced quality when the optional dependency is missing.
    """
    if doublemetaphone:
        primary, _ = doublemetaphone(word.lower())
        return primary or word.lower()
    # Simple fallback – not as accurate but functional
    return word.lower()

# ---------------------------------------------------------------------------
# Levenshtein distance (pure Python implementation)
# ---------------------------------------------------------------------------

def levenshtein(a: str, b: str) -> int:
    """Compute the classic Levenshtein edit distance between two strings.

    This implementation is O(len(a) * len(b)) and works for short IPA strings.
    """
    if a == b:
        return 0
    if not a:
        return len(b)
    if not b:
        return len(a)

    previous_row = list(range(len(b) + 1))
    for i, ca in enumerate(a, start=1):
        current_row = [i]
        for j, cb in enumerate(b, start=1):
            insertions = previous_row[j] + 1
            deletions = current_row[j - 1] + 1
            substitutions = previous_row[j - 1] + (ca != cb)
            current_row.append(min(insertions, deletions, substitutions))
        previous_row = current_row
    return previous_row[-1]
