(function () {
  const units = Array.from(document.querySelectorAll('section.unit'));
  const lectures = units.filter(s => s.classList.contains('lecture'));
  const navLinks = Array.from(document.querySelectorAll('#nav a[href^="#"]'));
  const byKey = new Map(GLOSSARY.map(g => [g.key, g]));
  const STORE = 'ai101-done';

  /* ---------- per-viewer progress (browser only) ---------- */
  function loadDone() {
    try { return new Set(JSON.parse(localStorage.getItem(STORE) || '[]')); } catch (e) { return new Set(); }
  }
  function saveDone(set) {
    try { localStorage.setItem(STORE, JSON.stringify([...set])); } catch (e) { /* storage unavailable */ }
  }
  let done = loadDone();

  function renderProgress() {
    document.querySelectorAll('#lectureNav a').forEach(a => {
      a.querySelector('.done').textContent = done.has(a.getAttribute('href').slice(1)) ? '✓' : '';
    });
    const n = lectures.filter(l => done.has(l.id)).length;
    document.getElementById('progText').textContent = n + ' of ' + lectures.length + ' lectures done';
    document.getElementById('progBar').style.width = (100 * n / lectures.length) + '%';
    document.querySelectorAll('.btn.mark').forEach(b => {
      const on = done.has(b.dataset.id);
      b.setAttribute('aria-pressed', on ? 'true' : 'false');
      b.textContent = on ? 'Done ✓' : 'Mark as done';
    });
  }

  /* ---------- lecture footers: previous / mark done / next ---------- */
  lectures.forEach((sec, i) => {
    const foot = document.createElement('div');
    foot.className = 'unit-foot';
    const prev = lectures[i - 1], next = lectures[i + 1];
    foot.innerHTML =
      (prev ? '<a class="btn" href="#' + prev.id + '">← ' + prev.dataset.title + '</a>' : '<a class="btn" href="#syllabus">← Syllabus</a>') +
      '<button class="btn mark" type="button" id="mark-' + sec.id + '" data-id="' + sec.id + '" aria-pressed="false">Mark as done</button>' +
      (next ? '<a class="btn" href="#' + next.id + '">' + next.dataset.title + ' →</a>' : '<a class="btn" href="#exam">Sample exam →</a>');
    sec.appendChild(foot);
  });
  document.addEventListener('click', e => {
    const b = e.target.closest('.btn.mark');
    if (!b) return;
    const id = b.dataset.id;
    done.has(id) ? done.delete(id) : done.add(id);
    saveDone(done);
    renderProgress();
  });

  /* ---------- glossary list ---------- */
  const sorted = GLOSSARY.slice().sort((a, b) => a.term.localeCompare(b.term, 'en', { sensitivity: 'base' }));
  const list = document.getElementById('glist');
  const letters = document.getElementById('gletters');
  function esc(s) { return s.replace(/[&<>"]/g, c => ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;' }[c])); }
  function lectRef(n) {
    if (!n) return '';
    const id = 'l' + String(n).padStart(2, '0');
    return '<a class="ref" href="#' + id + '">L' + n + '</a>';
  }
  function renderGlossary(q) {
    q = (q || '').trim().toLowerCase();
    let html = '', last = '', count = 0;
    const seen = new Set();
    sorted.forEach(g => {
      const hay = (g.term + ' ' + g.de + ' ' + g.definition).toLowerCase();
      if (q && !hay.includes(q)) return;
      count++;
      const L = g.term[0].toUpperCase();
      if (L !== last) { html += '<div class="gl-letter" id="gl-' + L + '">' + L + '</div>'; last = L; seen.add(L); }
      html += '<dt id="g-' + g.key + '">' + esc(g.term) + '<span class="de" lang="de">' + esc(g.de) + '</span></dt>' +
              '<dd>' + esc(g.definition) + lectRef(g.lecture) + '</dd>';
    });
    list.innerHTML = html || '<dd>No term matches “' + esc(q) + '”.</dd>';
    document.getElementById('gcount').textContent = q ? count + ' of ' + GLOSSARY.length + ' terms' : GLOSSARY.length + ' terms';
    letters.innerHTML = Array.from(new Set(sorted.map(g => g.term[0].toUpperCase())))
      .map(L => seen.has(L) ? '<a href="#gl-' + L + '" data-letter="' + L + '">' + L + '</a>' : '<span style="opacity:.35">' + L + '</span>').join('');
  }
  renderGlossary('');
  document.getElementById('gsearch').addEventListener('input', e => renderGlossary(e.target.value));
  letters.addEventListener('click', e => {
    const a = e.target.closest('a[data-letter]');
    if (!a) return;
    e.preventDefault();
    const t = document.getElementById('gl-' + a.dataset.letter);
    if (t) t.scrollIntoView({ behavior: 'smooth', block: 'start' });
  });

  /* ---------- glossary tooltips on highlighted terms ---------- */
  const tip = document.getElementById('tip');
  document.querySelectorAll('[data-g]').forEach(el => {
    if (!byKey.has(el.dataset.g)) return;
    el.setAttribute('tabindex', '0');
    el.setAttribute('role', 'link');
  });
  function showTip(el) {
    const g = byKey.get(el.dataset.g);
    if (!g) return;
    tip.innerHTML = '<b>' + esc(g.term) + '</b> <span lang="de" style="opacity:.7">(' + esc(g.de) + ')</span><br>' + esc(g.definition);
    tip.hidden = false;
    const r = el.getBoundingClientRect();
    const w = Math.min(352, document.documentElement.clientWidth - 32);
    tip.style.maxWidth = w + 'px';
    let left = r.left + window.scrollX;
    left = Math.max(16, Math.min(left, window.scrollX + document.documentElement.clientWidth - w - 16));
    tip.style.left = left + 'px';
    tip.style.top = (r.bottom + window.scrollY + 8) + 'px';
  }
  function hideTip() { tip.hidden = true; }
  document.addEventListener('mouseover', e => { const el = e.target.closest('[data-g]'); if (el) showTip(el); });
  document.addEventListener('mouseout', e => { if (e.target.closest('[data-g]')) hideTip(); });
  document.addEventListener('focusin', e => { const el = e.target.closest('[data-g]'); if (el) showTip(el); });
  document.addEventListener('focusout', hideTip);
  function openTerm(el) {
    hideTip();
    document.getElementById('gsearch').value = '';
    renderGlossary('');
    location.hash = 'g-' + el.dataset.g;
  }
  document.addEventListener('click', e => { const el = e.target.closest('[data-g]'); if (el) openTerm(el); });
  document.addEventListener('keydown', e => {
    if (e.key === 'Enter' && e.target.matches && e.target.matches('[data-g]')) openTerm(e.target);
  });

  /* ---------- maths: typeset each section once, when first shown ---------- */
  const typeset = new Set();
  function typesetUnit(sec) {
    if (typeset.has(sec.id)) return;
    const run = () => {
      if (!window.MathJax || !MathJax.typesetPromise) return;
      typeset.add(sec.id);
      MathJax.typesetPromise([sec]).catch(() => typeset.delete(sec.id));
    };
    if (window.MathJax && MathJax.startup && MathJax.startup.promise) MathJax.startup.promise.then(run);
    else window.addEventListener('load', () => { if (MathJax.startup) MathJax.startup.promise.then(run); }, { once: true });
  }

  /* ---------- router: one unit visible at a time ---------- */
  function route() {
    const h = decodeURIComponent(location.hash.slice(1));
    let target = units.find(u => u.id === h);
    let scrollTo = null;
    if (!target && (h.startsWith('g-') || h.startsWith('gl-'))) {
      target = document.getElementById('glossary');
      scrollTo = document.getElementById(h);
    }
    if (!target) target = units[0];
    units.forEach(u => u.classList.toggle('active', u === target));
    navLinks.forEach(a => {
      if (a.getAttribute('href') === '#' + target.id) a.setAttribute('aria-current', 'page');
      else a.removeAttribute('aria-current');
    });
    typesetUnit(target);
    hideTip();
    if (scrollTo) {
      scrollTo.scrollIntoView({ block: 'center' });
      scrollTo.style.transition = 'background .6s';
      scrollTo.style.background = 'var(--mark)';
      setTimeout(() => { scrollTo.style.background = ''; }, 1400);
    } else {
      window.scrollTo(0, 0);
    }
    const nav = document.getElementById('nav');
    nav.classList.remove('open');
    document.getElementById('navToggle').setAttribute('aria-expanded', 'false');
  }
  window.addEventListener('hashchange', route);

  document.getElementById('navToggle').addEventListener('click', () => {
    const nav = document.getElementById('nav');
    const open = nav.classList.toggle('open');
    document.getElementById('navToggle').setAttribute('aria-expanded', open ? 'true' : 'false');
  });

  renderProgress();
  route();
})();
