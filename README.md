# MySQL Android Connection

## Overview

Example demonstration showing how to work with a web server using MySQL and PHP.

## Usage

Download projects and change credentials as instructed in comments.

## Getting Started

Follow these steps to set up the required server components and use your Android app.

### Prerequisites

- Android Studio installed on your development machine.
- A web server with PHP and MySQL support.

### Server Setup

1. **Web Server:**
   - Set up a web server with PHP support. You can use XAMPP, WampServer, or any other web server of your choice.

2. **MySQL Database:**
   - Create a MySQL database for your app.
   - Import the provided SQL script (`database.sql`) into your database to set up the required tables.

3. **Configure PHP Script:**
   - Open `api.php` and update the MySQL connection parameters with your server details.

### Android App Setup

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/prakhardoneria/MySQL-Connector-for-Android.git
   cd MySQL-Connector-for-Android
   ```
### Open Project in Android Studio:

- Launch Android Studio.
- Open the project located in the android-app directory.

### Update API URL:

- In the DBHelper.java file, update the API_URL with the URL of your api.php script.

### Run the App:

- Connect your Android device or use an emulator.
- Click on the "Run" button in Android Studio to install and run the app.


# Server Security (SSL Certificate)

## Generate SSL Certificate:

- Obtain an SSL certificate for your server domain. You can use Let's Encrypt for free certificates.
- Make sure your SSL certificate is properly configured on your server.

## Download Certificate Chain (PEM file):

- Download the certificate chain (PEM file) from your certificate provider.
- Save the PEM file on your local machine.

## Upload PEM File to Server:

- Upload the PEM file to your server and ensure it is accessible.
- Save the same file in your project also in res/raw folder
