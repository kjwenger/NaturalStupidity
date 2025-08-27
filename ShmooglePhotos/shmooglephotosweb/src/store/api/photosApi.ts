import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'
import type { RootState } from '../index'

const baseQuery = fetchBaseQuery({
  baseUrl: '/api',
  prepareHeaders: (headers, { getState }) => {
    const token = (getState() as RootState).auth.token
    if (token) {
      headers.set('authorization', `Bearer ${token}`)
    }
    return headers
  },
})

export const photosApi = createApi({
  reducerPath: 'photosApi',
  baseQuery,
  tagTypes: ['Photo', 'Album', 'User'],
  endpoints: (builder) => ({
    getPhotos: builder.query<any, { page?: number; limit?: number; search?: string; album?: string }>({
      query: ({ page = 1, limit = 20, search, album }) => ({
        url: 'photos',
        params: { page, limit, search, album },
      }),
      providesTags: ['Photo'],
    }),
    
    uploadPhoto: builder.mutation<any, FormData>({
      query: (formData) => ({
        url: 'photos/upload',
        method: 'POST',
        body: formData,
      }),
      invalidatesTags: ['Photo'],
    }),
    
    deletePhoto: builder.mutation<any, string>({
      query: (photoId) => ({
        url: `photos/${photoId}`,
        method: 'DELETE',
      }),
      invalidatesTags: ['Photo'],
    }),
    
    updatePhoto: builder.mutation<any, { id: string; data: any }>({
      query: ({ id, data }) => ({
        url: `photos/${id}`,
        method: 'PUT',
        body: data,
      }),
      invalidatesTags: ['Photo'],
    }),
    
    searchPhotos: builder.query<any, string>({
      query: (query) => ({
        url: 'photos/search',
        params: { q: query },
      }),
      providesTags: ['Photo'],
    }),
    
    getAlbums: builder.query<any, void>({
      query: () => 'albums',
      providesTags: ['Album'],
    }),
    
    createAlbum: builder.mutation<any, { name: string; description?: string }>({
      query: (albumData) => ({
        url: 'albums',
        method: 'POST',
        body: albumData,
      }),
      invalidatesTags: ['Album'],
    }),
    
    getUserProfile: builder.query<any, void>({
      query: () => 'users/me',
      providesTags: ['User'],
    }),
    
    login: builder.mutation<any, { email: string; password: string }>({
      query: (credentials) => ({
        url: 'auth/login',
        method: 'POST',
        body: credentials,
      }),
    }),
    
    register: builder.mutation<any, { email: string; password: string; name: string }>({
      query: (userData) => ({
        url: 'auth/register',
        method: 'POST',
        body: userData,
      }),
    }),
  }),
})

export const {
  useGetPhotosQuery,
  useUploadPhotoMutation,
  useDeletePhotoMutation,
  useUpdatePhotoMutation,
  useSearchPhotosQuery,
  useGetAlbumsQuery,
  useCreateAlbumMutation,
  useGetUserProfileQuery,
  useLoginMutation,
  useRegisterMutation,
} = photosApi