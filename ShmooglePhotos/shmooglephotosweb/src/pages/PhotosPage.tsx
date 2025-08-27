import { useState } from 'react'
import { Grid, List, Filter, Download, Trash2, Heart, Share2, MoreHorizontal } from 'lucide-react'
import { useAppSelector, useAppDispatch } from '../store'
import { 
  setViewMode, 
  togglePhotoSelection, 
  clearSelection,
  toggleFavorite
} from '../store/slices/photosSlice'
import { useGetPhotosQuery } from '../store/api/photosApi'

const PhotosPage = () => {
  const [showFilters, setShowFilters] = useState(false)
  
  const dispatch = useAppDispatch()
  const { 
    selectedPhotos, 
    viewMode, 
    searchQuery, 
    currentAlbum 
  } = useAppSelector((state) => state.photos)

  const { 
    data: photosData, 
    isLoading, 
    error 
  } = useGetPhotosQuery({
    search: searchQuery,
    album: currentAlbum || undefined
  })

  const photos = photosData?.data || []

  const handlePhotoClick = (photoId: string, event: React.MouseEvent) => {
    if (event.ctrlKey || event.metaKey) {
      dispatch(togglePhotoSelection(photoId))
    }
  }

  const handleSelectAll = () => {
    if (selectedPhotos.length === photos.length) {
      dispatch(clearSelection())
    } else {
      photos.forEach((photo: any) => {
        if (!selectedPhotos.includes(photo.id)) {
          dispatch(togglePhotoSelection(photo.id))
        }
      })
    }
  }

  if (isLoading) {
    return (
      <div className="flex items-center justify-center h-64">
        <div className="animate-spin rounded-full h-32 w-32 border-b-2 border-primary-500"></div>
      </div>
    )
  }

  if (error) {
    return (
      <div className="text-center py-12">
        <p className="text-red-600">Failed to load photos. Please try again.</p>
      </div>
    )
  }

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-6">
      {/* Header */}
      <div className="flex items-center justify-between mb-6">
        <div>
          <h1 className="text-2xl font-bold text-gray-900">
            {currentAlbum ? `Album: ${currentAlbum}` : 'All Photos'}
          </h1>
          <p className="text-sm text-gray-600 mt-1">
            {photos.length} {photos.length === 1 ? 'photo' : 'photos'}
            {selectedPhotos.length > 0 && (
              <span className="ml-2">
                • {selectedPhotos.length} selected
              </span>
            )}
          </p>
        </div>

        <div className="flex items-center space-x-2">
          {/* Selection Actions */}
          {selectedPhotos.length > 0 && (
            <div className="flex items-center space-x-2 mr-4">
              <button className="p-2 text-gray-600 hover:bg-gray-100 rounded-lg transition-colors">
                <Download size={20} />
              </button>
              <button className="p-2 text-gray-600 hover:bg-gray-100 rounded-lg transition-colors">
                <Share2 size={20} />
              </button>
              <button className="p-2 text-gray-600 hover:bg-gray-100 rounded-lg transition-colors">
                <Heart size={20} />
              </button>
              <button className="p-2 text-red-600 hover:bg-red-50 rounded-lg transition-colors">
                <Trash2 size={20} />
              </button>
            </div>
          )}

          {/* View Controls */}
          <button
            onClick={() => setShowFilters(!showFilters)}
            className="p-2 text-gray-600 hover:bg-gray-100 rounded-lg transition-colors"
          >
            <Filter size={20} />
          </button>
          
          <div className="flex bg-gray-100 rounded-lg p-1">
            <button
              onClick={() => dispatch(setViewMode('grid'))}
              className={`p-1.5 rounded ${
                viewMode === 'grid' 
                  ? 'bg-white text-primary-600 shadow-sm' 
                  : 'text-gray-600 hover:text-gray-900'
              }`}
            >
              <Grid size={18} />
            </button>
            <button
              onClick={() => dispatch(setViewMode('list'))}
              className={`p-1.5 rounded ${
                viewMode === 'list' 
                  ? 'bg-white text-primary-600 shadow-sm' 
                  : 'text-gray-600 hover:text-gray-900'
              }`}
            >
              <List size={18} />
            </button>
          </div>
        </div>
      </div>

      {/* Filters Panel */}
      {showFilters && (
        <div className="bg-white border border-gray-200 rounded-lg p-4 mb-6">
          <div className="flex flex-wrap gap-4">
            <select className="border border-gray-300 rounded-lg px-3 py-2 text-sm">
              <option>All dates</option>
              <option>Today</option>
              <option>This week</option>
              <option>This month</option>
              <option>This year</option>
            </select>
            <select className="border border-gray-300 rounded-lg px-3 py-2 text-sm">
              <option>All types</option>
              <option>Photos</option>
              <option>Videos</option>
              <option>Screenshots</option>
            </select>
            <select className="border border-gray-300 rounded-lg px-3 py-2 text-sm">
              <option>All locations</option>
              <option>With location</option>
              <option>Without location</option>
            </select>
          </div>
        </div>
      )}

      {/* Selection Bar */}
      {photos.length > 0 && (
        <div className="flex items-center justify-between mb-4">
          <label className="flex items-center space-x-2">
            <input
              type="checkbox"
              checked={selectedPhotos.length === photos.length}
              onChange={handleSelectAll}
              className="rounded border-gray-300 text-primary-600 focus:ring-primary-500"
            />
            <span className="text-sm text-gray-600">Select all</span>
          </label>

          <div className="flex items-center space-x-2 text-sm text-gray-600">
            <span>Sort by:</span>
            <select className="border border-gray-300 rounded px-2 py-1 text-sm">
              <option>Date created</option>
              <option>Date modified</option>
              <option>Name</option>
              <option>Size</option>
            </select>
          </div>
        </div>
      )}

      {/* Photos Grid */}
      {photos.length > 0 ? (
        <div className={
          viewMode === 'grid'
            ? 'grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 xl:grid-cols-6 gap-2'
            : 'space-y-2'
        }>
          {photos.map((photo: any) => (
            <div
              key={photo.id}
              className={`group relative cursor-pointer ${
                selectedPhotos.includes(photo.id) ? 'ring-2 ring-primary-500' : ''
              } ${
                viewMode === 'grid'
                  ? 'aspect-square bg-gray-100 rounded-lg overflow-hidden'
                  : 'flex items-center space-x-4 p-3 bg-white border border-gray-200 rounded-lg hover:bg-gray-50'
              }`}
              onClick={(e) => handlePhotoClick(photo.id, e)}
            >
              {viewMode === 'grid' ? (
                <>
                  <img
                    src={photo.thumbnailUrl || photo.url}
                    alt={photo.filename}
                    className="w-full h-full object-cover"
                    loading="lazy"
                  />
                  
                  {/* Overlay */}
                  <div className="absolute inset-0 bg-black bg-opacity-0 group-hover:bg-opacity-20 transition-all duration-200">
                    {/* Selection checkbox */}
                    <div className="absolute top-2 left-2">
                      <input
                        type="checkbox"
                        checked={selectedPhotos.includes(photo.id)}
                        onChange={(e) => {
                          e.stopPropagation()
                          dispatch(togglePhotoSelection(photo.id))
                        }}
                        className="rounded border-white text-primary-600 bg-white bg-opacity-80"
                      />
                    </div>

                    {/* Actions */}
                    <div className="absolute top-2 right-2 opacity-0 group-hover:opacity-100 transition-opacity">
                      <button
                        onClick={(e) => {
                          e.stopPropagation()
                          dispatch(toggleFavorite(photo.id))
                        }}
                        className={`p-1.5 rounded-full ${
                          photo.isFavorite 
                            ? 'bg-red-500 text-white' 
                            : 'bg-white bg-opacity-80 text-gray-700 hover:bg-opacity-100'
                        }`}
                      >
                        <Heart size={16} fill={photo.isFavorite ? 'currentColor' : 'none'} />
                      </button>
                    </div>

                    {/* More actions */}
                    <div className="absolute bottom-2 right-2 opacity-0 group-hover:opacity-100 transition-opacity">
                      <button className="p-1.5 bg-white bg-opacity-80 hover:bg-opacity-100 rounded-full text-gray-700">
                        <MoreHorizontal size={16} />
                      </button>
                    </div>
                  </div>
                </>
              ) : (
                <>
                  <input
                    type="checkbox"
                    checked={selectedPhotos.includes(photo.id)}
                    onChange={(e) => {
                      e.stopPropagation()
                      dispatch(togglePhotoSelection(photo.id))
                    }}
                    className="rounded border-gray-300 text-primary-600"
                  />
                  <img
                    src={photo.thumbnailUrl || photo.url}
                    alt={photo.filename}
                    className="w-16 h-16 object-cover rounded-lg"
                  />
                  <div className="flex-1 min-w-0">
                    <p className="text-sm font-medium text-gray-900 truncate">
                      {photo.filename}
                    </p>
                    <p className="text-xs text-gray-500">
                      {new Date(photo.metadata.dateCreated).toLocaleDateString()} • {Math.round(photo.metadata.size / 1024)} KB
                    </p>
                  </div>
                  <div className="flex items-center space-x-1">
                    <button
                      onClick={(e) => {
                        e.stopPropagation()
                        dispatch(toggleFavorite(photo.id))
                      }}
                      className={`p-1.5 rounded ${
                        photo.isFavorite ? 'text-red-500' : 'text-gray-400 hover:text-gray-600'
                      }`}
                    >
                      <Heart size={16} fill={photo.isFavorite ? 'currentColor' : 'none'} />
                    </button>
                    <button className="p-1.5 text-gray-400 hover:text-gray-600 rounded">
                      <MoreHorizontal size={16} />
                    </button>
                  </div>
                </>
              )}
            </div>
          ))}
        </div>
      ) : (
        <div className="text-center py-12">
          <div className="w-16 h-16 bg-gray-100 rounded-full flex items-center justify-center mx-auto mb-4">
            <Grid className="w-8 h-8 text-gray-400" />
          </div>
          <h3 className="text-lg font-medium text-gray-900 mb-2">No photos found</h3>
          <p className="text-gray-600 mb-6">
            {searchQuery 
              ? `No photos match "${searchQuery}". Try a different search term.`
              : 'Upload your first photos to get started.'
            }
          </p>
          {!searchQuery && (
            <button className="bg-primary-600 text-white px-6 py-2 rounded-lg hover:bg-primary-700 transition-colors">
              Upload Photos
            </button>
          )}
        </div>
      )}
    </div>
  )
}

export default PhotosPage