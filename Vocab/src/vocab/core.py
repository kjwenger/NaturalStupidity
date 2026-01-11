"""Core logic for the Italian mnemonic generator.

The module provides:
* ``find_similar_pairs`` – discover phonologically similar word pairs.
* ``generate_mnemonic``   – call an LLM to obtain a short mnemonic.
* ``run_generation``      – high‑level helper used by the CLI.
"""

from __future__ import annotations

import json
import os
from pathlib import Path
from typing import List, Tuple

from .models import WordInfo, SimilarPair, Mnemonic
from .utils import (
    load_language_data,
    phonetic_key,
    levenshtein,
)

# ---------------------------------------------------------------------------
# Similarity detection
# ---------------------------------------------------------------------------

def _pair_score(w1: WordInfo, w2: WordInfo) -> float:
    """Return a normalized distance score between two words.

    The function uses Levenshtein distance on the IPA strings when both are present;
    otherwise it falls back to the raw word forms.  The result is divided by the length of
    the longer string so that scores are comparable across different lengths.
    """
    a = w1.ipa or w1.word.lower()
    b = w2.ipa or w2.word.lower()
    dist = levenshtein(a, b)
    norm = max(len(a), len(b))
    return dist / norm if norm else 0.0


def find_similar_pairs(
    words: List[WordInfo], *, max_distance: float = 0.2
) -> List[SimilarPair]:
    """Return a list of ``SimilarPair`` objects whose normalized distance ≤ *max_distance*.

    The algorithm is O(n²) but the expected word list size for a single language is modest
    (a few thousand entries at most), which keeps execution time acceptable.
    """
    pairs: List[SimilarPair] = []
    n = len(words)
    for i in range(n):
        w1 = words[i]
        key1 = phonetic_key(w1.word)
        for j in range(i + 1, n):
            w2 = words[j]
            # Quick filter: identical phonetic keys are required for similarity
            if key1 != phonetic_key(w2.word):
                continue
            score = _pair_score(w1, w2)
            if score <= max_distance:
                pairs.append(SimilarPair(w1=w1, w2=w2, score=score))
    # Sort by most similar (lowest score) first
    return sorted(pairs, key=lambda p: p.score)

# ---------------------------------------------------------------------------
# LLM integration – simple wrapper around OpenAI‑compatible API
# ---------------------------------------------------------------------------

def _load_cache(cache_path: Path) -> dict:
    if cache_path.is_file():
        try:
            return json.loads(cache_path.read_text(encoding="utf-8"))
        except Exception:
            return {}
    return {}

def _save_cache(cache_path: Path, data: dict) -> None:
    cache_path.parent.mkdir(parents=True, exist_ok=True)
    cache_path.write_text(json.dumps(data, ensure_ascii=False, indent=2), encoding="utf-8")


def generate_mnemonic(pair: SimilarPair, *, cache_dir: Path | None = None) -> Mnemonic:
    """Generate a mnemonic for *pair* using the configured LLM.

    Results are cached on disk (``cache_dir``/`<w1>_<w2>.json`).  If a cached entry exists it is returned
    directly, avoiding another API call.
    """
    cache_dir = cache_dir or Path(".cache")
    cache_file = cache_dir / f"{pair.w1.word}_{pair.w2.word}.json"
    if cache_file.is_file():
        cached = json.loads(cache_file.read_text(encoding="utf-8"))
        return Mnemonic(pair=pair, text=cached["mnemonic"])

    # Build the prompt – keep it short for token efficiency
    prompt = (
        f"Create a vivid mnemonic (≤ 120 characters) that links the two Italian words "
        f"'{pair.w1.word}' ({pair.w1.definition}) and '{pair.w2.word}' ({pair.w2.definition})."
    )

    # Lazy import – OpenAI may not be installed until this function runs.
    try:
        import openai  # type: ignore
    except ImportError as exc:
        raise RuntimeError(
            "openai package is required for LLM calls. Install with 'pip install openai'."
        ) from exc

    # The endpoint and key are read from the environment (handled by Qwen‑Code settings).
    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",  # default – can be overridden via env var OPENAI_MODEL
        messages=[{"role": "user", "content": prompt}],
        temperature=0.7,
        max_tokens=120,
    )
    text = response.choices[0].message.content.strip()
    cache_file.parent.mkdir(parents=True, exist_ok=True)
    cache_file.write_text(json.dumps({"mnemonic": text}, ensure_ascii=False, indent=2), encoding="utf-8")
    return Mnemonic(pair=pair, text=text)

# ---------------------------------------------------------------------------
# High‑level generation helper used by the CLI
# ---------------------------------------------------------------------------

def run_generation(lang: str = "it", output_dir: Path | None = None) -> List[Mnemonic]:
    """Load data for *lang*, find similar pairs, generate mnemonics and write results.

    The function returns the list of ``Mnemonic`` objects.  If *output_dir* is provided the
    JSON and Markdown reports are written there; otherwise they are placed in a folder named after the language
    (e.g., ``Italiano/``).
    """
    base_path = Path(__file__).resolve().parents[2] / lang.capitalize()
    words = load_language_data(base_path)
    pairs = find_similar_pairs(words)
    mnemonics: List[Mnemonic] = []
    for pair in pairs:
        mnemonics.append(generate_mnemonic(pair))

    # Write output files
    out_dir = output_dir or base_path / "mnemonics"
    out_dir.mkdir(parents=True, exist_ok=True)
    json_path = out_dir / f"{lang}_mnemonics.json"
    json_path.write_text(
        json.dumps([
            {"word1": m.pair.w1.word, "word2": m.pair.w2.word, "mnemonic": m.text}
            for m in mnemonics
        ], ensure_ascii=False, indent=2),
        encoding="utf-8",
    )
    # Optional markdown report (simple table)
    md_path = out_dir / f"{lang}_mnemonics.md"
    with md_path.open("w", encoding="utf-8") as f:
        f.write("# Mnemonic Report\n\n| Word 1 | Word 2 | Mnemonic | Score |\n|---|---|---|---|\n")
        for m in mnemonics:
            f.write(
                f"| {m.pair.w1.word} | {m.pair.w2.word} | {m.text.replace('|', '\\|')} | {m.pair.score:.3f} |\n"
            )
    return mnemonics
