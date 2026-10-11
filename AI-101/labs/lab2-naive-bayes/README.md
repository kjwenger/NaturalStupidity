# Lab 2: Naive Bayes spam filter

*Set after lecture 5. Due two weeks later.*

Build a naive Bayes classifier from scratch (no scikit-learn) on a public SMS spam dataset.

- Tokenise, build the vocabulary, estimate $P(\text{word} \mid \text{class})$ with Laplace smoothing; compute in log space.
- Report precision, recall and F1 on a held-out test set; plot precision against recall as you vary the decision threshold.
- List the 20 words with the highest spam-to-ham likelihood ratio.

**Reflection:** Which threshold would you deploy, given that a lost legitimate message costs more than a missed spam?

## Hand-in

Every lab is handed in as a repository folder with the code, a `README.md` with how to run it, a short write-up of results (one page), the answer to the reflection question, and a statement of which AI tools you used and for what. Grading: 60% correctness, 25% write-up, 15% reflection.

Starter code will follow in this folder.
