#!/bin/bash

# Create required directories
mkdir -p uploads themes plugins

# Start Docker containers
docker-compose up -d

echo "WordPress is starting up..."
echo "WordPress will be available at: http://localhost:8080"
echo "PHPMyAdmin will be available at: http://localhost:8081"
echo "Database credentials:"
echo "  Host: localhost:3306"
echo "  Database: wordpress"
echo "  Username: wordpress"
echo "  Password: wordpress"

# Wait for containers to be ready
echo "Waiting for containers to be ready..."
sleep 10

# Check if WordPress is responding
if curl -s http://localhost:8080 > /dev/null; then
    echo "✅ WordPress is ready!"
    echo "Visit http://localhost:8080 to complete the installation"
else
    echo "⚠️  WordPress may still be starting up. Please wait a moment and try again."
fi
