# ShmooglePhotos Architecture

## System Overview

ShmooglePhotos follows a modern, microservices-based architecture designed for scalability, maintainability, and performance. The system is built with a clear separation of concerns, allowing independent scaling and development of different components.

## High-Level Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Web Client    │    │  Mobile Client  │    │  Desktop Client │
│   (React SPA)   │    │ (React Native)  │    │   (Electron)    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌─────────────────┐
                    │   API Gateway   │
                    │    (Kong/Nginx) │
                    └─────────────────┘
                                 │
         ┌───────────────────────┼───────────────────────┐
         │                       │                       │
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Auth Service  │    │  Photos Service │    │   AI Service    │
│   (Node.js)     │    │   (Node.js)     │    │   (Python)      │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌─────────────────┐
                    │    Database     │
                    │  (PostgreSQL)   │
                    └─────────────────┘
                                 │
                    ┌─────────────────┐
                    │  File Storage   │
                    │   (S3/MinIO)    │
                    └─────────────────┘
```

## Core Components

### 1. Client Applications

#### Web Client (React SPA)
- **Technology**: React 18+ with TypeScript
- **State Management**: Redux Toolkit + RTK Query
- **Styling**: Tailwind CSS with custom components
- **Build Tool**: Vite for fast development and builds
- **Key Features**:
  - Responsive design for all screen sizes
  - Progressive Web App (PWA) capabilities
  - Infinite scroll for photo galleries
  - Drag-and-drop file uploads
  - Real-time updates via WebSocket

#### Mobile Client (React Native)
- **Technology**: React Native 0.72+ with TypeScript
- **State Management**: Shared Redux store with web client
- **Navigation**: React Navigation 6
- **Key Features**:
  - Native camera integration
  - Background photo upload
  - Push notifications
  - Offline photo viewing
  - Biometric authentication

#### Desktop Client (Electron)
- **Technology**: Electron with React renderer
- **Key Features**:
  - System tray integration
  - File system watching for auto-upload
  - Native OS notifications
  - Keyboard shortcuts

### 2. API Gateway

#### Kong/Nginx API Gateway
- **Responsibilities**:
  - Request routing and load balancing
  - Authentication and authorization
  - Rate limiting and throttling
  - Request/response transformation
  - Logging and monitoring
  - SSL/TLS termination

### 3. Backend Services

#### Authentication Service
- **Technology**: Node.js with Express
- **Database**: PostgreSQL with Prisma ORM
- **Key Features**:
  - JWT-based authentication
  - OAuth2 integration (Google, Facebook, Apple)
  - Two-factor authentication (2FA)
  - Session management
  - Role-based access control (RBAC)

#### Photos Service
- **Technology**: Node.js with Express
- **Database**: PostgreSQL with optimized schemas
- **Key Features**:
  - Photo/video upload handling
  - Metadata extraction (EXIF, GPS, timestamps)
  - Thumbnail generation (multiple sizes)
  - Album management
  - Sharing and permissions
  - Search indexing

#### AI Service
- **Technology**: Python with FastAPI
- **ML Frameworks**: TensorFlow, PyTorch, OpenCV
- **Key Features**:
  - Object detection and classification
  - Face recognition and clustering
  - OCR text extraction
  - Natural language query processing
  - Automatic tagging and categorization
  - Similarity search using embeddings

### 4. Data Layer

#### Primary Database (PostgreSQL)
```sql
-- Core tables structure
Users (id, email, name, settings, created_at)
Photos (id, user_id, filename, path, metadata, uploaded_at)
Albums (id, user_id, name, description, created_at)
PhotoAlbums (photo_id, album_id, added_at)
Tags (id, name, category, confidence)
PhotoTags (photo_id, tag_id, confidence)
Faces (id, photo_id, embedding, person_id)
Shares (id, resource_id, resource_type, permissions, expires_at)
```

#### File Storage (S3/MinIO)
- **Structure**:
  ```
  /users/{user_id}/
    /originals/{photo_id}.{ext}
    /thumbnails/{photo_id}_{size}.webp
    /processed/{photo_id}_{version}.{ext}
  ```
- **Features**:
  - Multiple storage tiers (hot, warm, cold)
  - CDN integration for fast global access
  - Automatic backup and replication
  - Encryption at rest and in transit

#### Cache Layer (Redis)
- **Use Cases**:
  - Session storage
  - Search result caching
  - Thumbnail caching
  - Rate limiting counters
  - Real-time notification queues

### 5. Background Processing

#### Job Queue System (Bull/BullMQ)
- **Job Types**:
  - Photo processing (thumbnail generation, metadata extraction)
  - AI analysis (tagging, face detection)
  - Search index updates
  - Backup operations
  - Cleanup tasks

#### Message Queue (Redis/RabbitMQ)
- **Event Types**:
  - Photo uploaded
  - Album created/modified
  - User actions for analytics
  - Notification triggers

## Data Flow

### Photo Upload Process
```
1. Client → API Gateway → Photos Service
2. Photos Service → Validate file → Store in temp location
3. Photos Service → Queue background jobs
4. Background Worker → Process photo (thumbnails, metadata)
5. AI Service → Analyze photo (tags, faces, objects)
6. Photos Service → Update database with results
7. Search Service → Update search index
8. Client ← Real-time update via WebSocket
```

### Search Query Process
```
1. Client → Search query → API Gateway → Photos Service
2. Photos Service → Parse query → Check cache
3. If not cached → Query database with full-text search
4. If AI query → Forward to AI Service for embedding search
5. Combine results → Apply permissions → Cache results
6. Return paginated results to client
```

## Security Architecture

### Authentication Flow
- JWT tokens with short expiration (15 minutes)
- Refresh tokens with longer expiration (7 days)
- Secure HTTP-only cookies for web clients
- Biometric/PIN authentication for mobile apps

### Authorization Model
- Resource-based permissions (read, write, share, delete)
- Inherited permissions from albums to photos
- Admin roles for system management
- API key authentication for service-to-service calls

### Data Protection
- End-to-end encryption for sensitive data
- TLS 1.3 for all communications
- Regular security audits and penetration testing
- GDPR compliance with data export/deletion

## Scalability Considerations

### Horizontal Scaling
- Stateless services allow easy horizontal scaling
- Load balancers distribute traffic across service instances
- Database read replicas for improved read performance
- CDN for global photo delivery

### Performance Optimization
- Image optimization (WebP format, multiple sizes)
- Lazy loading with intersection observers
- Infinite scroll with virtualization
- Service worker caching for offline support

### Monitoring and Observability
- **Logging**: Structured logging with ELK stack
- **Metrics**: Prometheus + Grafana dashboards
- **Tracing**: Distributed tracing with Jaeger
- **Alerting**: PagerDuty integration for critical issues
- **Health Checks**: Kubernetes liveness/readiness probes

## Deployment Architecture

### Container Strategy
- Docker containers for all services
- Multi-stage builds for optimized images
- Distroless base images for security
- Container registry with vulnerability scanning

### Kubernetes Deployment
- Microservices deployed as separate pods
- Horizontal Pod Autoscaler (HPA) based on CPU/memory
- Ingress controllers for external access
- Persistent volumes for database storage
- ConfigMaps and Secrets for configuration

### CI/CD Pipeline
```
GitHub → Actions → Build → Test → Security Scan → Deploy
                     ↓
              Container Registry
                     ↓
               Kubernetes Cluster
```

## Future Architecture Considerations

### Planned Enhancements
- **Event Sourcing**: For audit trails and data consistency
- **CQRS**: Separate read/write models for better performance
- **GraphQL**: For more flexible client-server communication
- **WebAssembly**: For client-side image processing
- **Edge Computing**: For faster global access

### Scalability Targets
- Support for 1M+ users
- 10TB+ photo storage per user
- Sub-second search response times
- 99.9% uptime SLA
- Multi-region deployment