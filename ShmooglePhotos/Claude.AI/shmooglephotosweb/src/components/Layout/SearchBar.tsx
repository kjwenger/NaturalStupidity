import { useState, useRef, useEffect } from 'react'
import { Search, X } from 'lucide-react'
import { useAppDispatch, useAppSelector } from '../../store'
import { setSearchQuery } from '../../store/slices/photosSlice'
import { useNavigate } from 'react-router-dom'

const SearchBar = () => {
  const [query, setQuery] = useState('')
  const [isFocused, setIsFocused] = useState(false)
  const inputRef = useRef<HTMLInputElement>(null)
  const dispatch = useAppDispatch()
  const navigate = useNavigate()
  const { searchQuery } = useAppSelector((state) => state.photos)

  useEffect(() => {
    setQuery(searchQuery)
  }, [searchQuery])

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    if (query.trim()) {
      dispatch(setSearchQuery(query.trim()))
      navigate('/search')
    }
  }

  const handleClear = () => {
    setQuery('')
    dispatch(setSearchQuery(''))
    inputRef.current?.focus()
  }

  const recentSearches = [
    'Beach photos',
    'Photos from 2023',
    'Dogs',
    'Family vacation',
    'Birthday party',
  ]

  const suggestions = [
    'Show me photos from last week',
    'Find pictures of cats',
    'Photos taken in Paris',
    'Screenshots',
    'Videos',
  ]

  return (
    <div className="relative w-full">
      <form onSubmit={handleSubmit} className="relative">
        <div className="relative">
          <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" size={20} />
          <input
            ref={inputRef}
            type="text"
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            onFocus={() => setIsFocused(true)}
            onBlur={() => setTimeout(() => setIsFocused(false), 200)}
            placeholder="Search photos..."
            className="w-full pl-10 pr-10 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-transparent"
          />
          {query && (
            <button
              type="button"
              onClick={handleClear}
              className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-400 hover:text-gray-600"
            >
              <X size={20} />
            </button>
          )}
        </div>
      </form>

      {/* Search Suggestions Dropdown */}
      {isFocused && (
        <div className="absolute top-full left-0 right-0 mt-1 bg-white border border-gray-200 rounded-lg shadow-lg z-50 max-h-96 overflow-y-auto">
          {query ? (
            <div className="p-2">
              <div className="px-3 py-2 text-sm text-gray-500 font-medium">
                Search suggestions
              </div>
              {suggestions
                .filter(suggestion => 
                  suggestion.toLowerCase().includes(query.toLowerCase())
                )
                .map((suggestion, index) => (
                  <button
                    key={index}
                    onClick={() => {
                      setQuery(suggestion)
                      dispatch(setSearchQuery(suggestion))
                      navigate('/search')
                    }}
                    className="w-full text-left px-3 py-2 text-sm text-gray-700 hover:bg-gray-100 rounded flex items-center space-x-2"
                  >
                    <Search size={16} className="text-gray-400" />
                    <span>{suggestion}</span>
                  </button>
                ))}
            </div>
          ) : (
            <div className="p-2">
              <div className="px-3 py-2 text-sm text-gray-500 font-medium">
                Recent searches
              </div>
              {recentSearches.map((search, index) => (
                <button
                  key={index}
                  onClick={() => {
                    setQuery(search)
                    dispatch(setSearchQuery(search))
                    navigate('/search')
                  }}
                  className="w-full text-left px-3 py-2 text-sm text-gray-700 hover:bg-gray-100 rounded flex items-center space-x-2"
                >
                  <Search size={16} className="text-gray-400" />
                  <span>{search}</span>
                </button>
              ))}
              
              <div className="border-t border-gray-100 mt-2 pt-2">
                <div className="px-3 py-1 text-xs text-gray-500 font-medium">
                  Try searching for:
                </div>
                <div className="flex flex-wrap gap-1 p-2">
                  {['People', 'Places', 'Things', 'Screenshots', 'Videos'].map((tag) => (
                    <button
                      key={tag}
                      onClick={() => {
                        setQuery(tag.toLowerCase())
                        dispatch(setSearchQuery(tag.toLowerCase()))
                        navigate('/search')
                      }}
                      className="px-2 py-1 text-xs bg-gray-100 text-gray-700 rounded hover:bg-gray-200 transition-colors"
                    >
                      {tag}
                    </button>
                  ))}
                </div>
              </div>
            </div>
          )}
        </div>
      )}
    </div>
  )
}

export default SearchBar