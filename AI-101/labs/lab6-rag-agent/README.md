# Lab 6: A RAG agent on your own router

*Set after lecture 13. Due two weeks later.*

Build a question-answering agent over the Markdown documentation in your NaturalStupidity and JoNAS repositories, using the LiteLLM router on JoNAS with a personal key (`scripts/llm-guest-key.sh add lab6 --models …`).

- Index: chunk the files, embed them with the `nomic-embed-text` model, store vectors in a NumPy array or SQLite.
- Agent loop with two tools: `search_docs(query)` and `read_file(path)`, using function calling.
- Write 15 test questions with known answers (e.g. "Which port does JoNAS's Caddy listen on publicly?"), and measure retrieval hit rate and answer accuracy.
- Try one prompt-injection test: add a document containing instructions and check whether your agent follows them.

**Reflection:** Where did your agent fail: retrieval, reasoning, or tool use? What single change helped most?

## Hand-in

Every lab is handed in as a repository folder with the code, a `README.md` with how to run it, a short write-up of results (one page), the answer to the reflection question, and a statement of which AI tools you used and for what. Grading: 60% correctness, 25% write-up, 15% reflection.

Starter code will follow in this folder.
