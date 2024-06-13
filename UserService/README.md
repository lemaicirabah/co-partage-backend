# UserService Microservice

## Description

A Spring Boot microservice for managing users, with a MySQL database backend.

## Requirements

- Java 11
- Maven
- MySQL

## Setup

1. **Clone the repository:**
   ```sh
   git clone <repository-url>
   cd user-service
   ```

## Configure the database:

1. Create a MySQL database named `user_db`.
2. Update the application.properties file with your MySQL username and password.
3. Build the project:
    ```sh
    mvn clean install
    ```
4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## API Endpoints:

- `GET /api/users` 
  - Retrieve all users
- `GET /api/users/{id}`
  - Retrieve a user by ID
- `POST /api/users`
  - Create a new user
- `PUT /api/users/{id}`
  - Update a user
- `DELETE /api/users/{id}`
  - Delete a user