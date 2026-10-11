# Lab 5: Measuring local inference

*Set after lecture 11. Due two weeks later.*

Benchmark the models in your LM Studio through its OpenAI-compatible API on `localhost:1234`.

- Measure prompt-processing (prefill) and generation (decode) speed for prompts of 1k, 8k, 32k and 128k tokens, for a dense model (Qwen3.8-27B) and an MoE model (e.g. Qwen3-Coder-30B-A3B).
- Compare decode speed with the bandwidth bound from example 11.1.
- Compare one model at two quantisation levels (e.g. Q4_K_M and Q8_0) on 20 questions of your own with known answers.
- Compute the KV-cache size per token from each model's `config.json` and check it against LM Studio's memory use.

**Reflection:** Which model would you choose for interactive coding, and which for overnight batch jobs? Justify with your numbers.

## Hand-in

Every lab is handed in as a repository folder with the code, a `README.md` with how to run it, a short write-up of results (one page), the answer to the reflection question, and a statement of which AI tools you used and for what. Grading: 60% correctness, 25% write-up, 15% reflection.

Starter code will follow in this folder.
