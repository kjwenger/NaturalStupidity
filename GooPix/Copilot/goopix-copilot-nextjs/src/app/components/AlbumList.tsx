'use client';

import { useSession } from 'next-auth/react';
import { useEffect, useState } from 'react';
import { Album, getAlbums } from '../services/photos';

export default function AlbumList() {
  const { data: session, status } = useSession();
  const [albums, setAlbums] = useState<Album[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    async function fetchAlbums() {
      if (status === 'authenticated' && session?.accessToken) {
        try {
          setLoading(true);
          setError(null);
          const albumData = await getAlbums(session.accessToken);
          setAlbums(albumData);
        } catch (e) {
          console.error('Error fetching albums:', e);
          setError(e instanceof Error ? e.message : 'Failed to fetch albums');
          setAlbums([]);
        } finally {
          setLoading(false);
        }
      }
    }

    fetchAlbums();
  }, [session, status]);

  if (status === 'loading') {
    return <div className="text-center p-4">Loading session...</div>;
  }

  if (status === 'unauthenticated') {
    return <div className="text-center p-4">Please sign in to view your albums</div>;
  }

  if (loading) {
    return <div className="text-center p-4">Loading albums...</div>;
  }

  if (error) {
    return <div className="text-center p-4 text-red-500">Error: {error}</div>;
  }

  if (albums.length === 0) {
    return <div className="text-center p-4">No albums found</div>;
  }

  return (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 p-4">
      {albums.map((album) => (
        <div
          key={album.id}
          className="border rounded-lg p-4 shadow hover:shadow-md transition-shadow"
        >
          {album.coverPhotoBaseUrl && (
            <img
              src={album.coverPhotoBaseUrl}
              alt={`Cover for ${album.title}`}
              className="w-full h-48 object-cover rounded-md mb-2"
            />
          )}
          <h3 className="font-semibold text-lg mb-1">{album.title}</h3>
          <p className="text-sm text-gray-600">
            {album.mediaItemsCount} items
          </p>
        </div>
      ))}
    </div>
  );
}