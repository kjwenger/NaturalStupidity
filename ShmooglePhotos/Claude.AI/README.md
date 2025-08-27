# ShmooglePhotos 📸

A privacy-focused, self-hosted alternative to Google Photos with AI-powered search and organization.

## Overview

ShmooglePhotos is a comprehensive photo management platform that provides all the features you love from Google Photos while keeping your data under your control. Built with modern web technologies and powered by advanced AI, it offers intelligent photo organization, natural language search, and seamless cross-platform synchronization.

## Features

### 🔍 Intelligent Search
- Natural language queries ("photos of dogs from last summer")
- Object and scene recognition
- Face detection and grouping
- OCR text extraction from images
- Location-based search

### 📱 Cross-Platform Access
- Responsive web application
- iOS and Android mobile apps
- Desktop synchronization
- Offline access capabilities

### 🤖 AI-Powered Organization
- Automatic album creation
- Smart categorization
- Duplicate detection and removal
- Timeline organization
- Event detection

### ✨ Advanced Editing
- Basic editing tools (crop, rotate, filters)
- AI-enhanced features (background removal, noise reduction)
- Batch editing operations
- Non-destructive editing
- RAW file support

### 🔒 Privacy & Security
- End-to-end encryption
- Self-hosted deployment
- No data mining or advertising
- GDPR compliant
- Role-based access control

### 🌐 Sharing & Collaboration
- Secure sharing links
- Album collaboration
- Permission management
- Social media integration
- Download protection

## Quick Start

### Prerequisites
- Docker and Docker Compose
- Node.js 18+ (for development)
- Python 3.9+ (for AI services)

### Installation

```bash
# Clone the repository
git clone https://github.com/kjwenger/ShmooglePhotos.git
cd ShmooglePhotos

# Start with Docker Compose
docker-compose up -d

# Or install locally
npm install
npm run build
npm start
```

### Configuration

1. Copy the example environment file:
```bash
cp .env.example .env
```

2. Configure your settings:
```env
# Database
DATABASE_URL=postgresql://user:password@localhost:5432/shmooglephotos

# Storage
STORAGE_PROVIDER=s3
AWS_S3_BUCKET=your-bucket-name
AWS_S3_REGION=us-east-1

# AI Services
OPENAI_API_KEY=your-openai-key
FACE_RECOGNITION_ENABLED=true
```

3. Initialize the database:
```bash
npm run db:migrate
```

4. Start the application:
```bash
npm run start
```

Visit `http://localhost:3000` to access the application.

## Development

### Project Structure
```
├── apps/
│   ├── web/           # React web application
│   ├── mobile/        # React Native mobile app
│   └── api/           # Node.js backend API
├── packages/
│   ├── shared/        # Shared utilities and types
│   ├── ai/            # AI/ML services
│   └── storage/       # Storage abstractions
├── docs/              # Documentation
└── infrastructure/    # Deployment configs
```

### Getting Started

1. Install dependencies:
```bash
npm install
```

2. Start development servers:
```bash
npm run dev
```

3. Run tests:
```bash
npm test
```

### API Documentation

The API documentation is available at `/api/docs` when running in development mode.

## Deployment

### Docker Deployment

```bash
# Production build
docker build -t shmooglephotos:latest .

# Deploy with docker-compose
docker-compose -f docker-compose.prod.yml up -d
```

### Kubernetes Deployment

```bash
# Apply Kubernetes manifests
kubectl apply -f infrastructure/k8s/
```

### Cloud Deployment

See our deployment guides:
- [AWS Deployment](docs/deployment/aws.md)
- [Google Cloud Deployment](docs/deployment/gcp.md)
- [Azure Deployment](docs/deployment/azure.md)

## Contributing

We welcome contributions! Please see our [Contributing Guide](CONTRIBUTING.md) for details.

### Development Workflow

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request

### Code Style

- Use TypeScript for all new code
- Follow the existing code style
- Run `npm run lint` before committing
- Write meaningful commit messages

## Support

- 📖 [Documentation](docs/)
- 🐛 [Issue Tracker](https://github.com/kjwenger/ShmooglePhotos/issues)
- 💬 [Discussions](https://github.com/kjwenger/ShmooglePhotos/discussions)
- 📧 [Contact](mailto:support@shmooglephotos.com)

## Roadmap

### Version 1.0 (Current)
- ✅ Basic photo upload and storage
- ✅ Web interface
- ✅ Basic search functionality
- ✅ User authentication

### Version 1.1 (Next)
- 🔄 AI-powered search
- 🔄 Face recognition
- 🔄 Mobile applications
- 🔄 Advanced editing tools

### Version 2.0 (Future)
- 📋 Real-time collaboration
- 📋 Video processing
- 📋 Advanced AI features
- 📋 Third-party integrations

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Inspired by Google Photos
- Built with ❤️ by the open-source community
- Special thanks to all contributors

---

**ShmooglePhotos** - Your photos, your privacy, your control.