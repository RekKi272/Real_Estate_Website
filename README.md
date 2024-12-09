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

## Screenshot Postman
### Guest
#### Get All Properties
<img width="1006" alt="Screenshot 2024-12-09 at 8 53 46 PM" src="https://github.com/user-attachments/assets/7c031b6b-3dca-4331-af34-9c1941c1a3e2">

#### Get Property By Filter
<img width="1001" alt="Screenshot 2024-12-09 at 7 35 50 PM" src="https://github.com/user-attachments/assets/5667b2bf-aaff-442e-acfe-9b0949341401">

#### Get Properties By Category, Service Type
<img width="987" alt="Screenshot 2024-12-09 at 8 57 54 PM" src="https://github.com/user-attachments/assets/e5494a3b-8b23-4008-8309-37af5f1235e3">

#### Get Details Of A Property
<img width="1000" alt="Screenshot 2024-12-09 at 9 01 08 PM" src="https://github.com/user-attachments/assets/67e53106-b0e0-436b-848c-72e09d563024">

<img width="1001" alt="Screenshot 2024-12-09 at 9 02 02 PM" src="https://github.com/user-attachments/assets/807f1b30-13a7-492b-8d46-51d3f0e62660">

## User

#### Get My Properties
<img width="1006" alt="Screenshot 2024-12-09 at 9 24 10 PM" src="https://github.com/user-attachments/assets/ecb058d4-451f-4b5d-879e-91e336407a9b">

<img width="997" alt="Screenshot 2024-12-09 at 9 24 49 PM" src="https://github.com/user-attachments/assets/90c3df9e-053d-4dcc-bcf5-e737a2fd9d19">

<img width="1002" alt="Screenshot 2024-12-09 at 9 25 23 PM" src="https://github.com/user-attachments/assets/235f7de8-e946-48fa-9585-a5c6e5a4c63b">

#### Get Details of My Property
<img width="1005" alt="Screenshot 2024-12-09 at 9 27 36 PM" src="https://github.com/user-attachments/assets/06674729-0164-4a75-b99c-3fff2ffa42e0">

<img width="993" alt="Screenshot 2024-12-09 at 9 28 05 PM" src="https://github.com/user-attachments/assets/4aea99d7-be5e-4ee8-a3cf-ccf0160cabbf">

#### Update My Property
Go into page updating property
<img width="996" alt="Screenshot 2024-12-09 at 9 31 03 PM" src="https://github.com/user-attachments/assets/2904090f-70e3-4735-ad13-75319be36c66">

<img width="1004" alt="Screenshot 2024-12-09 at 9 31 29 PM" src="https://github.com/user-attachments/assets/5d79be4e-50a4-4fd7-be6c-8864f182d4a5">

Update property successfully
<img width="1030" alt="Screenshot 2024-12-09 at 9 40 13 PM" src="https://github.com/user-attachments/assets/a0110470-1435-46aa-8a3f-286d7d2eb528">

####  Insert property
Successfully Post Property (Success Message)
<img width="988" alt="Screenshot 2024-12-09 at 9 49 54 PM" src="https://github.com/user-attachments/assets/485e34ba-f72e-42aa-969f-c2ef38f09a1c">

Show New Property In My Properties Page
<img width="998" alt="Screenshot 2024-12-09 at 9 59 59 PM" src="https://github.com/user-attachments/assets/99c5caea-8e02-4508-9c76-a52462041677">

## Admin

#### Get All Packages
<img width="995" alt="Screenshot 2024-12-09 at 10 04 34 PM" src="https://github.com/user-attachments/assets/e96b5e86-5b02-482a-a961-69c192427d9e">

#### Update Package
Go into page updating package
<img width="1005" alt="Screenshot 2024-12-09 at 10 06 25 PM" src="https://github.com/user-attachments/assets/f6fb5ffd-084b-4d82-b680-a915c2808838">

Successfully Update package
<img width="997" alt="Screenshot 2024-12-09 at 10 09 59 PM" src="https://github.com/user-attachments/assets/fe85e9a3-041f-488d-9ff2-fe77a688e787">

<img width="1001" alt="Screenshot 2024-12-09 at 10 10 53 PM" src="https://github.com/user-attachments/assets/a03ece36-9245-4ae5-ae98-b6144eb785d6">

#### Insert Package
Successfully Add Package (message)
<img width="1004" alt="Screenshot 2024-12-09 at 10 14 28 PM" src="https://github.com/user-attachments/assets/c1529610-1187-4ec6-aad8-80c35e6c9683">

Show New Package In Package List
<img width="994" alt="Screenshot 2024-12-09 at 10 14 57 PM" src="https://github.com/user-attachments/assets/e42e19f0-9a24-4753-8e26-50da6d32aaba">

## Video demo
Link: https://youtu.be/5DOYtY65IpU?si=Ae0PoLR6dJVxuvt3
