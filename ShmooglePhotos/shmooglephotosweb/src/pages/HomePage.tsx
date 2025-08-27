import { Link } from 'react-router-dom'
import { Camera, Search, Share2, Shield, Smartphone, Zap } from 'lucide-react'

const HomePage = () => {
  const features = [
    {
      icon: <Camera className="w-8 h-8 text-primary-500" />,
      title: 'Smart Photo Management',
      description: 'Automatically organize your photos with AI-powered tagging and smart albums.'
    },
    {
      icon: <Search className="w-8 h-8 text-primary-500" />,
      title: 'Intelligent Search',
      description: 'Find your photos instantly with natural language search and visual recognition.'
    },
    {
      icon: <Share2 className="w-8 h-8 text-primary-500" />,
      title: 'Easy Sharing',
      description: 'Share photos and albums securely with friends and family.'
    },
    {
      icon: <Shield className="w-8 h-8 text-primary-500" />,
      title: 'Privacy First',
      description: 'Your photos stay private and secure with end-to-end encryption.'
    },
    {
      icon: <Smartphone className="w-8 h-8 text-primary-500" />,
      title: 'Mobile & Web',
      description: 'Access your photos anywhere with our responsive web app and mobile apps.'
    },
    {
      icon: <Zap className="w-8 h-8 text-primary-500" />,
      title: 'Lightning Fast',
      description: 'Quick uploads, instant search, and smooth navigation across all your photos.'
    }
  ]

  return (
    <div className="min-h-screen">
      {/* Hero Section */}
      <section className="bg-gradient-to-br from-primary-600 to-primary-700 text-white py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center">
            <h1 className="text-4xl md:text-6xl font-bold mb-6">
              Your Photos, 
              <br className="hidden md:block" />
              <span className="text-primary-100">Your Privacy</span>
            </h1>
            <p className="text-xl md:text-2xl text-primary-100 mb-8 max-w-3xl mx-auto">
              A privacy-focused photo management platform with AI-powered search and organization. 
              All the features you love, with complete control over your data.
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center">
              <Link
                to="/register"
                className="bg-white text-primary-600 hover:bg-primary-50 px-8 py-3 rounded-lg text-lg font-semibold transition-colors"
              >
                Get Started Free
              </Link>
              <Link
                to="/demo"
                className="border-2 border-white text-white hover:bg-white hover:text-primary-600 px-8 py-3 rounded-lg text-lg font-semibold transition-colors"
              >
                View Demo
              </Link>
            </div>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="py-20 bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-3xl md:text-4xl font-bold text-gray-900 mb-4">
              Everything you need to manage your photos
            </h2>
            <p className="text-xl text-gray-600 max-w-3xl mx-auto">
              Powerful features that make organizing, finding, and sharing your photos effortless and secure.
            </p>
          </div>
          
          <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
            {features.map((feature, index) => (
              <div key={index} className="text-center p-6 rounded-lg hover:shadow-lg transition-shadow">
                <div className="flex justify-center mb-4">
                  {feature.icon}
                </div>
                <h3 className="text-xl font-semibold text-gray-900 mb-2">
                  {feature.title}
                </h3>
                <p className="text-gray-600">
                  {feature.description}
                </p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Stats Section */}
      <section className="py-20 bg-gray-50">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid md:grid-cols-3 gap-8 text-center">
            <div>
              <div className="text-4xl md:text-5xl font-bold text-primary-600 mb-2">1M+</div>
              <div className="text-lg text-gray-600">Photos Organized</div>
            </div>
            <div>
              <div className="text-4xl md:text-5xl font-bold text-primary-600 mb-2">50K+</div>
              <div className="text-lg text-gray-600">Happy Users</div>
            </div>
            <div>
              <div className="text-4xl md:text-5xl font-bold text-primary-600 mb-2">99.9%</div>
              <div className="text-lg text-gray-600">Uptime</div>
            </div>
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="py-20 bg-primary-600 text-white">
        <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <h2 className="text-3xl md:text-4xl font-bold mb-6">
            Ready to take control of your photos?
          </h2>
          <p className="text-xl text-primary-100 mb-8">
            Join thousands of users who have organized their photo libraries with ShmooglePhotos.
          </p>
          <Link
            to="/register"
            className="bg-white text-primary-600 hover:bg-primary-50 px-8 py-3 rounded-lg text-lg font-semibold transition-colors inline-block"
          >
            Start Your Free Account
          </Link>
          <p className="text-primary-200 text-sm mt-4">
            No credit card required • 15GB free storage • Cancel anytime
          </p>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-gray-900 text-gray-300 py-12">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid md:grid-cols-4 gap-8">
            <div>
              <div className="flex items-center space-x-2 mb-4">
                <div className="w-8 h-8 bg-primary-500 rounded-lg flex items-center justify-center">
                  <span className="text-white font-bold text-sm">SP</span>
                </div>
                <span className="text-xl font-semibold text-white">ShmooglePhotos</span>
              </div>
              <p className="text-gray-400">
                Privacy-focused photo management for everyone.
              </p>
            </div>
            
            <div>
              <h3 className="text-white font-semibold mb-4">Product</h3>
              <ul className="space-y-2">
                <li><Link to="/features" className="hover:text-white transition-colors">Features</Link></li>
                <li><Link to="/pricing" className="hover:text-white transition-colors">Pricing</Link></li>
                <li><Link to="/demo" className="hover:text-white transition-colors">Demo</Link></li>
              </ul>
            </div>
            
            <div>
              <h3 className="text-white font-semibold mb-4">Company</h3>
              <ul className="space-y-2">
                <li><Link to="/about" className="hover:text-white transition-colors">About</Link></li>
                <li><Link to="/blog" className="hover:text-white transition-colors">Blog</Link></li>
                <li><Link to="/contact" className="hover:text-white transition-colors">Contact</Link></li>
              </ul>
            </div>
            
            <div>
              <h3 className="text-white font-semibold mb-4">Support</h3>
              <ul className="space-y-2">
                <li><Link to="/help" className="hover:text-white transition-colors">Help Center</Link></li>
                <li><Link to="/privacy" className="hover:text-white transition-colors">Privacy Policy</Link></li>
                <li><Link to="/terms" className="hover:text-white transition-colors">Terms of Service</Link></li>
              </ul>
            </div>
          </div>
          
          <div className="border-t border-gray-800 mt-8 pt-8 text-center text-gray-400">
            <p>&copy; 2024 ShmooglePhotos. All rights reserved.</p>
          </div>
        </div>
      </footer>
    </div>
  )
}

export default HomePage