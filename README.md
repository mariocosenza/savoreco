# Savoreco: Sustainable Food Delivery
<p>
<a href= "https://github.com/mariocosenza/savoreco/releases/latest">
<img alt="Release Badge" src="https://img.shields.io/badge/release-1.0.0-brightgreen">
</a>
</p>

Welcome to **Savoreco**, the innovative web platform dedicated to delivering delicious, 
eco-friendly food right to your doorstep.
Our mission is to provide a seamless connection between 
health-conscious consumers and sustainable food sources, 
ensuring every meal supports both your well-being and the planet's.

## Features

- **Green Cuisine:** Diverse menu with organic, locally-sourced, and seasonal ingredients.
- **Eco Packaging:** Orders delivered in biodegradable or recyclable packaging.
- **Carbon Neutral Delivery:** Offsetting carbon footprint through eco-friendly projects.
- **Community Support:** Profits shared with local farms and green initiatives.

## Technical Setup

### Built With

- **Gradle**: Automated build system.
- **Tomcat 10.1.24**: Servlet container for web application deployment.
- **Amazon Corretto JDK 21**: Latest Java features and performance enhancements.


## Build Instructions
To build the WAR file, use the following command in your terminal:

```bash
gradle run
```

The WAR file will be located in the `build/libs` directory.

**Deployment**

Deploy the WAR file to Tomcat's `webapps` directory and access the application at:
```
http://localhost:8080/yourAppName
```
## Configuring Tomcat and Hibernate for PostgreSQL

### Tomcat Data Source Configuration
To integrate PostgreSQL with Tomcat, 
you need to define a JNDI data source in Tomcat's `context.xml` file. 
This involves specifying the database connection properties, such as the driver class, URL, username, and password. 
You will also set parameters like `maxActive`, `maxIdle`, and `maxWait` to manage the connection pool.

### Hibernate Configuration Update
For Hibernate, you must update the `hibernate.cfg.xml` file to reflect the 
PostgreSQL database settings. 
This includes setting the correct dialect, driver class, and connection details. 
You'll also configure session management and, optionally, 
enable the display of SQL statements in the console for debugging purposes.

Remember to secure your database credentials. 
Instead of hardcoding them in your configuration files, especially for production, 
use environment variables or a secure configuration management system to handle sensitive information safely.

**Amazon S3 and Radar API Configuration**

Create a `bucket.cfg.xml` file in the `resources` directory with your Amazon S3 bucket keys:
```xml
<properties>
    <property name="accessKey">YOUR_ACCESS_KEY</property>
    <property name="ssecretKey">YOUR_SECRET_KEY</property>
</properties>

```
For Radar API, update the keys accordingly:
```xml
<properties>
    <property name="radarAPI.publicKey">YOUR_PUBLIC_API_KEY</property>
</properties>
```
Note: Do not commit real credentials to version control. Use environment variables or a secure secrets management service.

**Engagement**

Customers: Sign up for a discount on your first order.
Partners: Collaborate with us for a sustainable future.
Investors: Contribute to greener tomorrow.

**Support**

For inquiries or feedback, email us at [info@savoreco.it](mailto:info@savoreco.it).

**Join us in revolutionizing food delivery with Savoreco.**

## Authors

- [@giuseppecavallaro](https://github.com/GiuseppeCava03)
- [@mariocosenza](https://github.com/mariocosenza)
- [@mariofasolino](https://github.com/MarioFas)
