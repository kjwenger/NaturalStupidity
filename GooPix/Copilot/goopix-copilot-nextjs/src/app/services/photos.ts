export interface Album {
  id: string;
  title: string;
  productUrl: string;
  mediaItemsCount: string;
  coverPhotoBaseUrl?: string;
}

export async function getAlbums(accessToken: string): Promise<Album[]> {
  try {
    const response = await fetch(
      'https://photoslibrary.googleapis.com/v1/albums?pageSize=50',
      {
        headers: {
          'Authorization': `Bearer ${accessToken}`,
          'Content-type': 'application/json',
        },
      }
    );

    const data = await response.json();

    if (!response.ok) {
      console.error('Google Photos API Error:', {
        status: response.status,
        statusText: response.statusText,
        data,
      });

      if (response.status === 403) {
        throw new Error(
          'Access denied. Note: This API can only access albums created by this application. ' +
          'For existing albums, you\'ll need to create them through this app first.'
        );
      }

      throw new Error(
        `Failed to fetch albums: ${response.status} ${response.statusText}` +
        (data.error?.message ? ` - ${data.error.message}` : '')
      );
    }

    if (!data.albums || data.albums.length === 0) {
      console.info('No albums found. Note: Only albums created by this app are accessible.');
    }

    return data.albums || [];
  } catch (error) {
    console.error('Error fetching albums:', error);
    throw error;
  }
}