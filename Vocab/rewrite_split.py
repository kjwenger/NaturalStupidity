import json, pathlib
split_dir = pathlib.Path('/com.github/kjwenger/NaturalStupidity/Vocab/Italiano/split')
for f in split_dir.glob('*.json'):
    data = json.load(f.open())
    lines = [json.dumps(item, ensure_ascii=False) for item in data]
    f.write_text('\n'.join(lines), encoding='utf-8')
print('Rewritten', len(list(split_dir.glob('*.json'))), 'files')