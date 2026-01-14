# NaturalStupidity
Playground for all things Artificial Intelligence

## Prerequisites

To manage Node.js versions, it's recommended to install `nvm` (Node Version Manager).

### Linux and macOS

To install `nvm` on Linux or macOS, you have a couple of options:

#### Using `curl` or `wget` (Recommended for fresh installs)

You can use the following `curl` or `wget` commands:

```bash
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash
```
or
```bash
wget -qO- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash
```

#### Using Homebrew (If Homebrew is already installed)

If you have Homebrew installed on macOS, you can install `nvm` using:

```bash
brew install nvm
```

After installing with Homebrew, you'll need to follow the instructions provided by Homebrew to set up `nvm` in your shell. This usually involves adding lines to your shell's profile file (e.g., `~/.bashrc`, `~/.zshrc`).

After installation (using either method), close and reopen your terminal, or source your shell's profile script (e.g., `source ~/.bashrc`, `source ~/.zshrc`).

### Windows

For Windows, it is recommended to use `nvm-windows`. You can download the latest installer from the [nvm-windows GitHub repository](https://github.com/coreybutler/nvm-windows/releases).

Follow the installation instructions provided on the GitHub page. After installation, you can use commands like:
```bash
nvm install latest
nvm use latest
```

### Using `nvm` to Install Node.js

After `nvm` is installed and configured, you can install Node.js versions.

To install the latest **LTS** (Long Term Support) version of Node.js and set it as default:
```bash
nvm install --lts
nvm alias default 'lts/*'
```

To install the **latest** available version of Node.js (which might not be LTS) and set it as default:
```bash
nvm install node
nvm alias default node
```

To use a specific installed version:
```bash
nvm use <version>
```

For example, to use Node.js version 18:
```bash
nvm use 18
```
