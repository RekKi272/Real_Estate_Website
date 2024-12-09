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
### User
#### Home Page
![image](https://github.com/user-attachments/assets/3b40f84a-05b6-4341-aa6e-edb5140475b2)
#### Listing Properties
![image](https://github.com/user-attachments/assets/ce0fdca4-1e60-484d-96c4-0fde39b61fa9)
#### Property detail view
![image](https://github.com/user-attachments/assets/bf217e6d-8b01-4b7a-8861-e87c44f660ff)
#### Login page
![image](https://github.com/user-attachments/assets/16cbd3fd-f1a1-4bdc-bd37-95b168a9138d)
#### Register page
![image](https://github.com/user-attachments/assets/70eca55a-7274-4915-8eef-f1a9d9c95d87)
### Search/Filter page
![image](https://github.com/user-attachments/assets/81d84ad3-a687-4d2b-bdb2-4c9ed4e6c528)
#### Pay package Service
![image](https://github.com/user-attachments/assets/e5db8d6e-6987-4e23-8082-17cdd0bc4b14)
### Admin
#### Manage Post page
![image](https://github.com/user-attachments/assets/f3be7531-4d3f-4c88-826e-de3b7aeddd07)
#### Manage User page
![image](https://github.com/user-attachments/assets/3526b221-a153-4860-9a15-bbd1bbe7deb2)
#### Manage Admin page
![image](https://github.com/user-attachments/assets/4989ab4e-d185-46bc-bd65-64276f189de3)
#### Add admin page
![image](https://github.com/user-attachments/assets/9fc7c99e-d57c-4bc1-8f98-366f4181bac4)
#### View Package page
![image](https://github.com/user-attachments/assets/87181d28-79f4-45d1-941a-2514d3d6aa60)
#### Add/Edit Package page
![image](https://github.com/user-attachments/assets/74fdb27f-12f1-47b8-aacb-494280bec397)
#### Commission Management
![image](https://github.com/user-attachments/assets/1bdfc1bf-8d1d-44e7-8538-2aeab05688e5)

## Video demo

