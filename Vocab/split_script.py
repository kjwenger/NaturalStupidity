import json, pathlib
base = pathlib.Path('/com.github/kjwenger/NaturalStupidity/Vocab/Italiano/italian_words.json')
words = json.load(base.open())

def first_two_cons(word):
    cons = []
    for ch in word.lower():
        if ch.isalpha() and ch not in 'aeiouàèéìòùâêîôûäëïöü':
            cons.append(ch)
            if len(cons) == 2:
                break
    return ''.join(cons) or word[:2]

groups = {}
for entry in words:
    key = first_two_cons(entry['word'])
    groups.setdefault(key, []).append(entry)
out_dir = base.parent / 'split'
out_dir.mkdir(exist_ok=True)
for k, lst in groups.items():
    fname = out_dir / f"{k}.json"
    # Write each record on its own line, wrapped as a JSON array with proper indentation (2 spaces)
    with fname.open('w', encoding='utf-8') as f:
        f.write('[\n')
        for i, item in enumerate(lst):
            line = json.dumps(item, ensure_ascii=False)
            # indent two spaces
            indented_line = '  ' + line
            if i < len(lst) - 1:
                f.write(f"{indented_line},\n")
            else:
                f.write(f"{indented_line}\n")
        f.write(']')
print('Created', len(groups), 'files in', out_dir)