import { createSlice, type PayloadAction } from '@reduxjs/toolkit'

interface Photo {
  id: string
  filename: string
  url: string
  thumbnailUrl: string
  metadata: {
    width: number
    height: number
    size: number
    dateCreated: string
    location?: {
      latitude: number
      longitude: number
    }
  }
  tags: string[]
  album?: string
  isFavorite: boolean
}

interface PhotosState {
  photos: Photo[]
  selectedPhotos: string[]
  currentAlbum: string | null
  viewMode: 'grid' | 'list'
  sortBy: 'date' | 'name' | 'size'
  sortOrder: 'asc' | 'desc'
  searchQuery: string
  isUploading: boolean
  uploadProgress: number
}

const initialState: PhotosState = {
  photos: [],
  selectedPhotos: [],
  currentAlbum: null,
  viewMode: 'grid',
  sortBy: 'date',
  sortOrder: 'desc',
  searchQuery: '',
  isUploading: false,
  uploadProgress: 0,
}

const photosSlice = createSlice({
  name: 'photos',
  initialState,
  reducers: {
    setPhotos: (state, action: PayloadAction<Photo[]>) => {
      state.photos = action.payload
    },
    addPhoto: (state, action: PayloadAction<Photo>) => {
      state.photos.unshift(action.payload)
    },
    removePhoto: (state, action: PayloadAction<string>) => {
      state.photos = state.photos.filter(photo => photo.id !== action.payload)
      state.selectedPhotos = state.selectedPhotos.filter(id => id !== action.payload)
    },
    togglePhotoSelection: (state, action: PayloadAction<string>) => {
      const photoId = action.payload
      if (state.selectedPhotos.includes(photoId)) {
        state.selectedPhotos = state.selectedPhotos.filter(id => id !== photoId)
      } else {
        state.selectedPhotos.push(photoId)
      }
    },
    clearSelection: (state) => {
      state.selectedPhotos = []
    },
    setViewMode: (state, action: PayloadAction<'grid' | 'list'>) => {
      state.viewMode = action.payload
    },
    setSortBy: (state, action: PayloadAction<'date' | 'name' | 'size'>) => {
      state.sortBy = action.payload
    },
    setSortOrder: (state, action: PayloadAction<'asc' | 'desc'>) => {
      state.sortOrder = action.payload
    },
    setSearchQuery: (state, action: PayloadAction<string>) => {
      state.searchQuery = action.payload
    },
    setCurrentAlbum: (state, action: PayloadAction<string | null>) => {
      state.currentAlbum = action.payload
    },
    setUploading: (state, action: PayloadAction<boolean>) => {
      state.isUploading = action.payload
    },
    setUploadProgress: (state, action: PayloadAction<number>) => {
      state.uploadProgress = action.payload
    },
    toggleFavorite: (state, action: PayloadAction<string>) => {
      const photo = state.photos.find(p => p.id === action.payload)
      if (photo) {
        photo.isFavorite = !photo.isFavorite
      }
    },
  },
})

export const {
  setPhotos,
  addPhoto,
  removePhoto,
  togglePhotoSelection,
  clearSelection,
  setViewMode,
  setSortBy,
  setSortOrder,
  setSearchQuery,
  setCurrentAlbum,
  setUploading,
  setUploadProgress,
  toggleFavorite,
} = photosSlice.actions

export default photosSlice.reducer