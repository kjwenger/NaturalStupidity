# ShmooglePhotos Web Application

A React-based web application for the ShmooglePhotos photo management platform.

## Features

- **Modern React Stack**: Built with React 18, TypeScript, and Vite
- **State Management**: Redux Toolkit with RTK Query for efficient data fetching
- **Responsive Design**: Tailwind CSS for beautiful, responsive UI
- **Authentication**: Secure login/logout with JWT tokens
- **Photo Management**: Grid and list views, search, filtering, and selection
- **Real-time Updates**: Live photo updates and notifications
- **Code Quality**: ESLint, Prettier, and TypeScript for maintainable code

## Tech Stack

- **Frontend**: React 18 with TypeScript
- **Build Tool**: Vite 7.x
- **Styling**: Tailwind CSS v4 with PostCSS
- **State Management**: Redux Toolkit + RTK Query
- **Routing**: React Router v7
- **UI Components**: Headless UI + Lucide React icons
- **Animations**: Framer Motion
- **Code Quality**: ESLint + Prettier

## Getting Started

### Prerequisites

- Node.js 20+ (LTS recommended)
- npm or yarn

### Node.js Setup with NVM (Recommended)

We recommend using Node Version Manager (NVM) to install and manage Node.js versions.

#### Install NVM

**Windows:**
1. Download and install nvm-windows from: https://github.com/coreybutler/nvm-windows/releases
2. Download the `nvm-setup.exe` file from the latest release
3. Run the installer as Administrator
4. Restart your terminal/command prompt

**macOS:**
```bash
# Install using Homebrew (recommended)
brew install nvm

# Or install using curl
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.0/install.sh | bash
```

**Linux:**
```bash
# Install using curl
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.0/install.sh | bash

# Or install using wget
wget -qO- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.0/install.sh | bash
```

After installation on macOS/Linux, restart your terminal or run:
```bash
source ~/.bashrc
# or
source ~/.zshrc
```

#### Install and Set Node.js Version

Once NVM is installed:

```bash
# Install Node.js 20 LTS
nvm install 20

# Set Node.js 20 as default
nvm use 20
nvm alias default 20

# Verify installation
node --version  # Should show v20.x.x
npm --version   # Should show npm version
```

### Installation

1. Install dependencies:
```bash
npm install
```

2. Start the development server:
```bash
npm run dev
```

The application will be available at `http://127.0.0.1:5173`

## Available Scripts

- `npm run dev` - Start development server
- `npm run build` - Build for production
- `npm run preview` - Preview production build
- `npm run lint` - Run ESLint
- `npm run lint:fix` - Fix ESLint issues
- `npm run format` - Format code with Prettier
- `npm run format:check` - Check code formatting
- `npm run type-check` - Run TypeScript type checking

## Project Structure

```
src/
├── components/          # Reusable UI components
│   ├── Layout/         # Layout components (Header, Layout, SearchBar)
│   └── ProtectedRoute.tsx
├── pages/              # Page components
│   ├── HomePage.tsx
│   ├── LoginPage.tsx
│   └── PhotosPage.tsx
├── store/              # Redux store configuration
│   ├── api/           # RTK Query API definitions
│   ├── slices/        # Redux slices
│   └── index.ts       # Store configuration
├── types/             # TypeScript type definitions
├── hooks/             # Custom React hooks
├── utils/             # Utility functions
├── App.tsx            # Main App component
└── main.tsx           # Application entry point
```

## Features Implemented

### Authentication
- [x] Login page with form validation
- [x] JWT token management
- [x] Protected routes
- [x] Auto-logout on token expiration
- [ ] Registration flow
- [ ] Password reset
- [ ] OAuth integration

### Photo Management
- [x] Photo grid/list views
- [x] Photo selection (single/multiple)
- [x] Basic search functionality
- [x] View mode switching
- [x] Favorite photos
- [ ] Photo upload
- [ ] Photo editing
- [ ] Bulk operations
- [ ] Albums management

### UI/UX
- [x] Responsive header with navigation
- [x] Search bar with suggestions
- [x] Loading states
- [x] Error handling
- [x] Tailwind CSS v4 styling system
- [ ] Dark/light theme support
- [ ] Keyboard shortcuts
- [ ] Drag & drop
- [ ] Infinite scroll

## Development Setup Notes

### Tailwind CSS v4 Configuration
This project uses Tailwind CSS v4, which requires specific setup:

```css
/* src/index.css */
@import "tailwindcss";
```

```js
// postcss.config.js
export default {
  plugins: {
    '@tailwindcss/postcss': {},
    autoprefixer: {},
  },
}
```

### Vite Configuration
The Vite config is optimized for development:

```js
export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    strictPort: false,
    host: '127.0.0.1',
  },
  optimizeDeps: {
    force: true,
  },
})
```

## Development

### Code Style

This project uses ESLint and Prettier for code formatting. The configuration includes:

- TypeScript strict mode
- React hooks rules
- Import/export best practices
- Consistent formatting

### State Management

The application uses Redux Toolkit for state management:

- **Auth Slice**: User authentication state
- **Photos Slice**: Photo data and UI state
- **RTK Query**: API calls and caching

### API Integration

RTK Query is used for efficient API integration:

- Automatic caching
- Background updates
- Optimistic updates
- Error handling

## Environment Variables

Create a `.env` file in the root directory:

```env
VITE_API_URL=http://localhost:3000/api
VITE_APP_NAME=ShmooglePhotos
```

## Building for Production

1. Build the application:
```bash
npm run build
```

2. Preview the production build:
```bash
npm run preview
```

The built files will be in the `dist/` directory.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Run tests and linting
5. Submit a pull request

## License

This project is licensed under the MIT License.
