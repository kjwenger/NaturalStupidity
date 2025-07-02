# Local WordPress Setup for Liederkranz Hohenwart Migration

## Quick Start

1. **Navigate to the wordpress directory:**
   ```bash
   cd /media/kjwenger/D/com.github/kjwenger/NaturalStupidity/LiterKranz/wordpress
   ```

2. **Start the WordPress environment:**
   ```bash
   docker-compose up -d
   ```

3. **Access WordPress:**
   - WordPress: http://localhost:8080
   - PHPMyAdmin: http://localhost:8081

## WordPress Installation Steps

1. **Open WordPress Setup:**
   - Visit http://localhost:8080
   - Follow the WordPress installation wizard

2. **Database Configuration:**
   - Database Name: `wordpress`
   - Username: `wordpress`
   - Password: `wordpress`
   - Database Host: `db`
   - Table Prefix: `wp_`

3. **Site Configuration:**
   - Site Title: `Liederkranz Hohenwart e.V.`
   - Username: `admin`
   - Password: Choose a strong password
   - Email: `info@liederkranz-hohenwart.de`

## Recommended Theme and Plugins

### Theme: Astra
1. Go to Appearance > Themes > Add New
2. Search for "Astra"
3. Install and Activate

### Essential Plugins to Install:

1. **WPML Multilingual CMS** (Premium - for trial, use Polylang free alternative)
   - For German/English multilingual support

2. **The Events Calendar**
   - For choir rehearsals and concerts

3. **NextGEN Gallery**
   - For photo galleries

4. **WPForms Lite**
   - For contact and membership forms

5. **Yoast SEO**
   - For search engine optimization

6. **UpdraftPlus**
   - For backups

## Initial Configuration Steps

### 1. Language Setup
1. Go to Settings > General
2. Set Site Language to "Deutsch"
3. Set Timezone to "Europe/Berlin"

### 2. Permalink Structure
1. Go to Settings > Permalinks
2. Select "Post name" structure
3. Save changes

### 3. Install Polylang (Free Multilingual Plugin)
1. Go to Plugins > Add New
2. Search for "Polylang"
3. Install and activate
4. Go to Languages > Languages
5. Add German (Deutsch) as default language
6. Add English (English) as secondary language

## Content Migration Plan

### Phase 1: Basic Pages (German)
1. Create these pages in German:
   - Startseite (Home)
   - Über uns (About)
   - Aktuell (Current Events)
   - Termine (Events)
   - Vorstand (Board)
   - Geschichte (History)
   - Kontakt (Contact)

### Phase 2: Content Import
1. Copy text content from `.website/www.liederkranz-hohenwart.de/`
2. Upload images to Media Library
3. Create photo galleries using NextGEN Gallery

### Phase 3: English Translations
1. Translate key pages to English
2. Set up language switcher in navigation

## File Locations

- **WordPress Files:** Docker volume `wordpress_data`
- **Uploads:** `./uploads` directory
- **Custom Themes:** `./themes` directory  
- **Custom Plugins:** `./plugins` directory

## Stopping the Environment

```bash
docker-compose down
```

## Accessing Database

- **PHPMyAdmin:** http://localhost:8081
- **Direct MySQL:** localhost:3306
- **Credentials:** wordpress/wordpress

## Troubleshooting

### Port Conflicts
If ports 8080 or 8081 are in use, edit `docker-compose.yml`:
```yaml
ports:
  - "8082:80"  # Change 8080 to 8082
```

### Reset Installation
```bash
docker-compose down
docker volume rm wordpress_wordpress_data wordpress_db_data
docker-compose up -d
```

## Next Steps for Trial Migration

1. Complete WordPress installation
2. Install and configure theme
3. Install essential plugins
4. Create basic page structure
5. Import sample content from original site
6. Test multilingual functionality
7. Set up photo galleries
8. Configure events calendar

This local setup provides a complete development environment to test the migration strategy before implementing on a live server.