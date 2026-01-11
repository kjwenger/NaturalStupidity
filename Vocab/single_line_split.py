import json, pathlib
split_dir = pathlib.Path('/com.github/kjwenger/NaturalStupidity/Vocab/Italiano/split')
for f in split_dir.glob('*.json'):
    # Load the file (it may be a JSON array or newline‑separated objects)
    try:
        data = json.load(f.open())
    except Exception:
        # Fallback: parse each non‑empty line as a JSON object
        lines = [ln.strip() for ln in f.read_text(encoding='utf-8').splitlines() if ln.strip()]
        data = []
        for ln in lines:
            try:
                data.append(json.loads(ln))
            except Exception:
                pass
    # Write each record on its own line (no surrounding array, no commas)
    f.write_text('\n'.join(json.dumps(item, ensure_ascii=False) for item in data), encoding='utf-8')
print('Rewritten', len(list(split_dir.glob('*.json'))), 'files to single‑line records')