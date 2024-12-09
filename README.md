# JavaTechnology_FinalProject
# Real Estate Website
A simple and intuitive real estate website for managing property listings, built using **Java Spring Boot** and **Thymeleaf**. This project includes features like property creation, posting, editing, deletion, and an admin panel for post and user manaagement.
## Used Technologies
* Spring (Boot, Data, Security, MVC, etc)
* JPA / Hibernate
* MySQL
* Thymeleaf
* Bootstrap, CSS, JS
* Maven
* Lombok
* SQL Query
* Paypal payment

## Access to website
### User account
Username: user1@gmail.com  
Password: 1234  

### Admin account
Username: admin1@gmail.com  
Password: admin

## About Project
### Features
  * Property Management
      * Create, edit, delete property listings.
      * Support for amenities, multiple images, and property types.
  * User Management
      * Admin panel for managing user accounts
  * Post Management
      * Publish, edit and decline property posts.
  * Reponsive Design
      * Mobile-friendly and easy-to-navigate interface
### Tech Stack
  * Backend: Java Spring Boot
      * Thymeleaf for server-side rendering
      * Spring Data JPA for database interaction
      * Spring Security for authentication
  * Database: MySQL
  * Frontend: HTML, CSS, Thymeleaf
### Usage
* User can register/login
* User can navigate on the main page browse properties or select a property from the showcase for more detail.
* User can search/filter for the property according to the specified criteria.
* User can post their own property to website and wait for acception from admin.
* Admin can manage post, user, dashboard and package-service (such as publish the post from user, adding new package-service, unable access to account, etc...).

## Getting Started
### Prerequisites
* Java 17 or higher
* Maven
* MySQL
### Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/RekKi272/JavaTechnology_FinalProject.git
2. **Set up the Database**:
   * Create a MySQL database, e.g., **realestate_db**.
   * Update application.properties with your MySQL credentials:
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/realestate_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
3. **Build the Project**:
   ```bash
   mvn clean install
4. **Run the Application**:
   ```bash
   mvn spring-boot:run
5. **Access the Application**:
  * Navigate to  *http://localhost:8080*
## Screen shot

## Video demo

