import SignInButton from "./components/SignInButton";
import AlbumList from "./components/AlbumList";

export default function Home() {
  return (
    <div className="min-h-screen p-8">
      <nav className="w-full max-w-7xl mx-auto mb-8">
        <SignInButton />
      </nav>
      <main className="max-w-7xl mx-auto">
        <AlbumList />
      </main>
    </div>
  );
}
