"""Data models for the mnemonic generator.

These are simple, typed containers used throughout the package.
"""

from __future__ import annotations

from dataclasses import dataclass
from typing import List


@dataclass(frozen=True)
class WordInfo:
    """Represents a single lexical entry."""

    word: str          # orthographic form
    ipa: str           # phonetic transcription (IPA)
    definition: str    # short meaning in the target language
    language: str = "it"  # ISO‑639‑1 code, default Italian for this phase


@dataclass(frozen=True)
class SimilarPair:
    """Two words that are phonologically similar."""

    w1: WordInfo
    w2: WordInfo
    score: float       # lower means more similar (e.g., normalized edit distance)


@dataclass
class Mnemonic:
    """Result of the LLM – a short memorable sentence linking the pair."""

    pair: SimilarPair
    text: str

