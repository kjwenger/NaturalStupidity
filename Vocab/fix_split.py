import json, pathlib
split_dir = pathlib.Path('/com.github/kjwenger/NaturalStupidity/Vocab/Italiano/split')
for f in split_dir.glob('*.json'):
    text = f.read_text(encoding='utf-8').strip()
    # If already a JSON array, skip
    if text.startswith('[') and text.endswith(']'):
        continue
    # Split into non‑empty lines and parse each as JSON object
    lines = [ln.strip() for ln in text.splitlines() if ln.strip()]
    objs = []
    for line in lines:
        try:
            objs.append(json.loads(line))
        except json.JSONDecodeError:
            # ignore malformed lines
            pass
    # Write proper JSON array (pretty printed)
    f.write_text(json.dumps(objs, ensure_ascii=False, indent=2), encoding='utf-8')
print('Fixed', len(list(split_dir.glob('*.json'))), 'files')