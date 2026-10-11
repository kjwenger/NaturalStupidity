# Lab 3: A neural network from scratch

*Set after lecture 7. Due two weeks later.*

Implement an MLP with NumPy only, including the forward pass, backpropagation, mini-batch SGD and Adam. Train it on MNIST.

- Verify your gradients against numerical finite differences on a tiny network.
- Reach at least 97% test accuracy with one hidden layer.
- Plot training and validation loss for three learning rates and for SGD vs. Adam; show one run that overfits and fix it with weight decay or early stopping.

**Reflection:** What did writing backprop by hand teach you that using PyTorch would not?

## Hand-in

Every lab is handed in as a repository folder with the code, a `README.md` with how to run it, a short write-up of results (one page), the answer to the reflection question, and a statement of which AI tools you used and for what. Grading: 60% correctness, 25% write-up, 15% reflection.

Starter code will follow in this folder.
