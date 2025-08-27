export interface User {
  id: string
  email: string
  name: string
  avatar?: string
  createdAt: string
  updatedAt: string
}

export interface Photo {
  id: string
  filename: string
  url: string
  thumbnailUrl: string
  metadata: {
    width: number
    height: number
    size: number
    dateCreated: string
    dateTaken?: string
    camera?: string
    location?: {
      latitude: number
      longitude: number
      address?: string
    }
    exif?: Record<string, any>
  }
  tags: string[]
  albumId?: string
  isFavorite: boolean
  isArchived: boolean
  userId: string
  createdAt: string
  updatedAt: string
}

export interface Album {
  id: string
  name: string
  description?: string
  coverPhoto?: string
  photoCount: number
  isShared: boolean
  userId: string
  createdAt: string
  updatedAt: string
}

export interface UploadProgress {
  fileName: string
  progress: number
  status: 'pending' | 'uploading' | 'processing' | 'completed' | 'error'
  error?: string
}

export interface SearchFilters {
  dateRange?: {
    start: Date
    end: Date
  }
  location?: {
    latitude: number
    longitude: number
    radius: number
  }
  tags?: string[]
  hasLocation: boolean
  isFavorite?: boolean
  fileType?: 'image' | 'video' | 'all'
}

export interface ApiResponse<T> {
  data: T
  message?: string
  success: boolean
}

export interface PaginatedResponse<T> {
  data: T[]
  pagination: {
    page: number
    limit: number
    total: number
    totalPages: number
  }
}