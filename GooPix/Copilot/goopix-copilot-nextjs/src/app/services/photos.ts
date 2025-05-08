export interface Album {
  id: string;
  title: string;
  productUrl: string;
  mediaItemsCount: string;
  coverPhotoBaseUrl?: string;
}

export async function getAlbums(accessToken: string): Promise<Album[]> {
  const response = await fetch(
    'https://photoslibrary.googleapis.com/v1/albums',
    {
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    }
  );

  if (!response.ok) {
    throw new Error('Failed to fetch albums');
  }

  const data = await response.json();
  return data.albums || [];
}