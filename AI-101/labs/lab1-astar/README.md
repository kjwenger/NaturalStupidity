# Lab 1: Route planner with A*

*Set after lecture 3. Due two weeks later.*

Implement BFS, uniform-cost search and A* on a grid map with walls and terrain costs (road 1, forest 3, swamp 8). Read maps from text files.

- Return the path, its cost and the number of expanded nodes for each algorithm.
- Implement two heuristics (Manhattan distance, and Manhattan × the minimum terrain cost) and show which are admissible.
- Plot the expanded nodes for each algorithm on a 100×100 map.

**Reflection:** Your A* with an inadmissible heuristic (Manhattan × 3) is 10× faster. When would you accept that trade-off?

## Hand-in

Every lab is handed in as a repository folder with the code, a `README.md` with how to run it, a short write-up of results (one page), the answer to the reflection question, and a statement of which AI tools you used and for what. Grading: 60% correctness, 25% write-up, 15% reflection.

Starter code will follow in this folder.
