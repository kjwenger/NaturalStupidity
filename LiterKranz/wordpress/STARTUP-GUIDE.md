# WordPress Migration Trial - Startup Guide

## Quick Start Instructions

### 1. Start WordPress Environment
```bash
cd wordpress
docker-compose up -d
```

### 2. Access WordPress
- **WordPress Installation:** http://localhost:8080
- **PHPMyAdmin (Database):** http://localhost:8081

### 3. Complete WordPress Installation

#### Database Configuration
- Database Name: `wordpress`
- Username: `wordpress`
- Password: `wordpress`
- Database Host: `db`
- Table Prefix: `wp_`

#### Site Settings
- Site Title: `Liederkranz Hohenwart e.V.`
- Admin Username: `admin`
- Admin Password: [choose strong password]
- Admin Email: `info@liederkranz-hohenwart.de`

### 4. Initial WordPress Configuration

#### Language and Locale
1. Go to **Settings > General**
2. Set **Site Language** to `Deutsch`
3. Set **Timezone** to `Europe/Berlin`
4. Set **Date Format** to `j. F Y`
5. Set **Time Format** to `H:i`

#### Permalinks
1. Go to **Settings > Permalinks**
2. Select **Post name** structure
3. Save changes

## Theme and Plugin Installation

### Install Astra Theme
1. **Appearance > Themes > Add New**
2. Search for "Astra"
3. Install and Activate

### Install Essential Plugins (in order)

#### 1. Polylang (Multilingual)
```
Plugins > Add New > Search "Polylang" > Install & Activate
Settings > Languages > Add Languages:
- German (Deutsch) - Default
- English - Secondary
```

#### 2. Classic Editor
```
Plugins > Add New > Search "Classic Editor" > Install & Activate
```

#### 3. The Events Calendar
```
Plugins > Add New > Search "The Events Calendar" > Install & Activate
Events > Settings > Timezone: Europe/Berlin
```

#### 4. NextGEN Gallery
```
Plugins > Add New > Search "NextGEN Gallery" > Install & Activate
```

#### 5. WPForms Lite
```
Plugins > Add New > Search "WPForms Lite" > Install & Activate
```

#### 6. Yoast SEO
```
Plugins > Add New > Search "Yoast SEO" > Install & Activate
Run setup wizard for German optimization
```

## Content Migration

### Create Page Structure

Create these pages (**Pages > Add New**):

#### German Pages
1. **Startseite** (Homepage) - Set as Front Page
2. **Über uns** (About Us)
3. **Aktuell** (Current Events)
4. **Termine** (Events)
5. **Vorstand** (Board)
6. **Zelter-Plakette** (Awards)
7. **Geschichte** (History)
8. **Kontakt** (Contact)

#### Sub-pages under Kontakt
- **Mitglied werden** (Become a Member)
- **Satzung** (Constitution)

#### Legal Pages
- **Impressum** (Legal Notice)
- **Datenschutzerklärung** (Privacy Policy)

### Copy Content from Templates

Use content from `content-templates.md` file to populate each page:

#### Homepage Content
Copy the German welcome message and chairman's greeting from the template.

#### About Us Content
Extract information about the 120-year history and current membership.

#### Contact Information
Include Walter Kreitmayr's contact details and rehearsal information.

## Media Migration

### Upload Key Images
1. **Media > Library > Add New**
2. Upload from `.website/www.liederkranz-hohenwart.de/images/`:
   - `logoliederkranzmitrand_579.jpg` (Logo)
   - `fahne_579.gif` (Flag)
   - `s2dlogo.jpg` (Banner)

### Set Up Photo Galleries
1. **Gallery > Add Gallery/Album**
2. Create albums:
   - **Aktuelle Ereignisse** (Current Events)
   - **Rückblick** (Archive)
   - **Geschichte** (History)

## Navigation Setup

### Create Primary Menu
1. **Appearance > Menus**
2. Create menu "Hauptnavigation"
3. Add pages in order:
   - Startseite
   - Aktuell
   - Über uns
   - Termine
   - Vorstand
   - Zelter-Plakette
   - Geschichte
   - Kontakt
4. Assign to "Primary Menu" location

### Add Language Switcher
1. **Appearance > Widgets**
2. Add "Language Switcher" to navigation area

## Forms Setup

### Contact Form
1. **WPForms > Add New**
2. Use "Contact Form" template
3. Add fields: Name, Email, Subject, Message
4. Configure notification to `info@liederkranz-hohenwart.de`
5. Add shortcode to Contact page

### Membership Application Form
1. **WPForms > Add New**
2. Create form with fields:
   - Personal Information
   - Contact Details
   - Musical Experience
   - Voice Type
3. Add to "Mitglied werden" page

## Events Calendar

### Set Up Recurring Rehearsal
1. **Events > Add New**
2. Title: "Chorprobe"
3. Date: Every Thursday
4. Location: Metzgerbräu Hohenwart
5. Set as recurring weekly event

### Add Calendar to Pages
1. Add calendar widget to sidebar
2. Create events page with calendar shortcode

## Multilingual Configuration

### Translate Key Pages
1. **Languages > Translations**
2. For each German page, create English version:
   - Home → Startseite
   - About Us → Über uns
   - Contact → Kontakt
   - Events → Termine

### Configure Language Settings
1. **Settings > Languages > Settings**
2. Enable "Hide URL language information for default language"
3. Set German as default language

## Final Testing

### Test All Functionality
- [ ] All pages load correctly
- [ ] Navigation menu works
- [ ] Language switcher functions
- [ ] Contact form sends emails
- [ ] Photo galleries display
- [ ] Events calendar works
- [ ] Mobile responsiveness
- [ ] German/English switching

## Stopping the Environment

When finished testing:
```bash
docker-compose down
```

## Accessing Your Work Later

To restart the environment with all your data:
```bash
cd wordpress
docker-compose up -d
```

All your WordPress content is preserved in Docker volumes.

## Next Steps

After successful local testing:
1. Document any issues or improvements
2. Plan hosting strategy for live deployment
3. Prepare German hosting environment
4. Set up domain migration plan
5. Configure production security measures

This trial migration provides a complete preview of the modernized Liederkranz Hohenwart website with all essential functionality while preserving the traditional German choir culture and community focus.