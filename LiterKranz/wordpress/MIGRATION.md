# Content Migration Guide for Liederkranz Hohenwart

## WordPress Installation Settings

When setting up WordPress at http://localhost:8080, use these settings:

### Database Configuration
- Database Name: `wordpress`
- Username: `wordpress`
- Password: `wordpress`
- Database Host: `db`
- Table Prefix: `wp_`

### Site Information
- Site Title: `Liederkranz Hohenwart e.V.`
- Username: `admin`
- Password: `[choose secure password]`
- Email: `info@liederkranz-hohenwart.de`

### WordPress Settings
- Language: `Deutsch (German)`
- Timezone: `Europe/Berlin`
- Date Format: `j. F Y` (German format)
- Time Format: `H:i`

## Theme and Plugin Installation Order

### 1. Install Astra Theme
1. Go to **Appearance > Themes > Add New**
2. Search for "Astra"
3. Install and Activate
4. Go to **Appearance > Astra Options**
5. Import starter template if desired

### 2. Essential Plugins Installation
Install these plugins in order:

#### A. Polylang (Free Multilingual)
1. **Plugins > Add New**
2. Search "Polylang"
3. Install and Activate
4. **Settings > Languages**
5. Add Languages:
   - **German (Deutsch)** - Set as default
   - **English** - Secondary language
6. **Settings > Languages > Settings**
   - Enable "The language is set from content"
   - Enable "Hide URL language information for default language"

#### B. Classic Editor (for easier content editing)
1. Search and install "Classic Editor"
2. Activate

#### C. The Events Calendar
1. Search and install "The Events Calendar"
2. Activate
3. Go to **Events > Settings**
4. Set timezone to Europe/Berlin

#### D. NextGEN Gallery
1. Search and install "NextGEN Gallery"
2. Activate

#### E. WPForms Lite
1. Search and install "WPForms Lite"
2. Activate

#### F. Yoast SEO
1. Search and install "Yoast SEO"
2. Activate
3. Run setup wizard
4. Configure for German market

## Page Structure Creation

### Main Navigation Pages (German)

Create these pages in WordPress (**Pages > Add New**):

1. **Startseite** (Homepage)
   - Slug: `startseite`
   - Set as Front Page in **Settings > Reading**

2. **Über uns** (About Us)
   - Slug: `ueber-uns`

3. **Aktuell** (Current Events)
   - Slug: `aktuell`

4. **Termine** (Events/Dates)
   - Slug: `termine`

5. **Vorstand** (Board/Leadership)
   - Slug: `vorstand`

6. **Zelter-Plakette** (Awards)
   - Slug: `zelter-plakette`

7. **Geschichte** (History)
   - Slug: `geschichte`

8. **Kontakt** (Contact)
   - Slug: `kontakt`

### Sub-pages

Create under **Kontakt**:
- **Mitglied werden** (Become a Member)
- **Satzung** (Constitution)

### Legal Pages
- **Impressum** (Legal Notice)
- **Datenschutzerklärung** (Privacy Policy)

## Content Migration Steps

### 1. Homepage Content (Startseite)
Copy content from: `.website/www.liederkranz-hohenwart.de/index.html`

**Key content to migrate:**
- Welcome message from Walter Kreitmayr
- Brief description of the choir
- Current member count (20 active members)
- Rehearsal information (Thursdays at Metzgerbräu)
- Contact information

### 2. About Us (Über uns)
Copy from: `.website/www.liederkranz-hohenwart.de/ueberuns/index.html`

**Content includes:**
- Choir history and tradition
- Mission and values
- Member information
- Group photo from 1999

### 3. Current Events (Aktuell)
Copy from: `.website/www.liederkranz-hohenwart.de/aktuell/index.php.html`

**Set up:**
- Recent news and announcements
- Photo gallery integration
- Link to archive/retrospective

### 4. Events/Dates (Termine)
Copy from: `.website/www.liederkranz-hohenwart.de/termine/index.php.html`

**Configure:**
- Regular rehearsal schedule
- Upcoming concerts
- Special events

### 5. Board (Vorstand)
Copy from: `.website/www.liederkranz-hohenwart.de/vorstand/index.php.html`

**Include:**
- Board member information
- Leadership structure
- Contact details

### 6. Awards (Zelter-Plakette)
Copy from: `.website/www.liederkranz-hohenwart.de/zelter/index.html`

**Content:**
- Award information
- Achievement history
- Recognition photos

### 7. History (Geschichte)
Copy from: `.website/www.liederkranz-hohenwart.de/geschichte/index.html`

**Include:**
- 120+ year history
- Founding information
- Historical milestones
- Historical photos

### 8. Contact (Kontakt)
Copy from: `.website/www.liederkranz-hohenwart.de/kontakt/index.html`

**Set up:**
- Contact form using WPForms
- Address and contact information
- Email links

## Image Migration

### Upload Images to WordPress
1. Go to **Media > Library**
2. Upload images from `.website/www.liederkranz-hohenwart.de/images/`

**Key images to upload:**
- `logoliederkranzmitrand_579.jpg` - Logo
- `fahne_579.gif` - Flag
- `s2dlogo.jpg` - Additional logo
- Board member photos
- Historical photos
- Event photos

### Photo Galleries Setup
1. **Gallery > Add Gallery/Album**
2. Create albums for:
   - **Aktuelle Ereignisse** (Current Events)
   - **Rückblick** (Retrospective/Archive)
   - **Geschichte** (Historical Photos)

3. Upload photos from:
   - `.website/www.liederkranz-hohenwart.de/aktuell/234107b1a50d1c801/`
   - `.website/www.liederkranz-hohenwart.de/aktuell/rueckblick/` (various folders)

## Navigation Menu Setup

### Primary Navigation (German)
1. Go to **Appearance > Menus**
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

### English Navigation
1. Create menu "Main Navigation"
2. Add English translations of pages
3. Configure with Polylang language switcher

## Multilingual Content

### After Polylang Setup
1. **Languages > Translations**
2. For each German page, create English translation:
   - Home
   - About Us
   - Current Events
   - Events
   - Board
   - Awards
   - History
   - Contact

### Language Switcher
1. **Appearance > Widgets**
2. Add "Language Switcher" widget to header or sidebar
3. **Appearance > Customize > Menus**
4. Add language switcher to navigation menu

## Forms Setup

### Contact Form
1. **WPForms > Add New**
2. Use "Contact Form" template
3. Add fields:
   - Name (required)
   - Email (required)
   - Subject
   - Message (required)
4. Configure email notifications to `info@liederkranz-hohenwart.de`

### Membership Application Form
1. **WPForms > Add New**
2. Create custom form with fields:
   - Personal information
   - Contact details
   - Musical experience
   - Voice type
3. Add to "Mitglied werden" page

## Events Calendar Setup

### Configure Events
1. **Events > Add New**
2. Create recurring event:
   - **Title:** Chorprobe (Choir Rehearsal)
   - **Date:** Every Thursday
   - **Time:** [Check original site for time]
   - **Venue:** Metzgerbräu, Hohenwart

3. Add upcoming concerts and special events

### Calendar Display
1. Add calendar widget to sidebar
2. Create events archive page
3. Add events to navigation menu

## Testing Checklist

### Functionality Tests
- [ ] All pages load correctly
- [ ] Navigation menu works
- [ ] Language switcher functions
- [ ] Contact form sends emails
- [ ] Photo galleries display properly
- [ ] Events calendar shows correctly
- [ ] Mobile responsiveness works

### Content Verification
- [ ] All German content migrated
- [ ] Key pages translated to English
- [ ] Images display correctly
- [ ] Links work properly
- [ ] Contact information is accurate

### SEO Configuration
- [ ] Page titles set in German/English
- [ ] Meta descriptions added
- [ ] Images have alt text
- [ ] Permalink structure configured
- [ ] XML sitemap generated

This guide provides a complete roadmap for migrating the Liederkranz Hohenwart website to WordPress while maintaining its German heritage and adding modern functionality.