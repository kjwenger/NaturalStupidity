# NaturalStupidity
Playground for all things Artificial Intelligence

## Prerequisites

To manage Node.js versions, it's recommended to install `nvm` (Node Version Manager).

### Linux and macOS

To install `nvm` on Linux or macOS, you can use the following `curl` or `wget` commands:

```bash
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash
```
or
```bash
wget -qO- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash
```

After installation, close and reopen your terminal, or source your shell's profile script (e.g., `source ~/.bashrc`, `source ~/.zshrc`).

You can then install the latest Node.js LTS version:
```bash
nvm install --lts
nvm use --lts
```

### Windows

For Windows, it is recommended to use `nvm-windows`. You can download the latest installer from the [nvm-windows GitHub repository](https://github.com/coreybutler/nvm-windows/releases).

Follow the installation instructions provided on the GitHub page. After installation, you can use commands like:
```bash
nvm install latest
nvm use latest
```
