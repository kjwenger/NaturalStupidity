# Lab 4: A tiny Transformer on your GPU

*Set after lecture 10. Due two weeks later.*

Train a character-level, decoder-only Transformer (about 10M parameters; Karpathy's nanoGPT is a good reference) on a German text corpus, such as Goethe's works from Project Gutenberg, on BosGameM5 with PyTorch for ROCm.

- Implement causal multi-head self-attention yourself; you may use PyTorch for everything else.
- Report training throughput (tokens/s), final validation loss and perplexity.
- Generate samples at temperatures 0.5, 1.0 and 1.5 and describe the difference.
- Visualise one attention head's weights on a sample sentence.

**Reflection:** How does your model's validation loss change if you halve the context length? Explain using lecture 10.

## Hand-in

Every lab is handed in as a repository folder with the code, a `README.md` with how to run it, a short write-up of results (one page), the answer to the reflection question, and a statement of which AI tools you used and for what. Grading: 60% correctness, 25% write-up, 15% reflection.

Starter code will follow in this folder.
