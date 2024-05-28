# Co-Partage - backend

## Table of Contents

- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Database Configuration](#database-configuration)
- [Running the Application](#running-the-application)
- [Endpoints](#endpoints)
- [Testing](#testing)
- [Deployment](#deployment)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Co-Partage system aims to facilitate collaboration among users for various types of collaborative projects. It aims
to
address the common challenge faced by many individuals who struggle to find suitable teammates for their projects. By
providing a user-friendly online platform, Co-Partage enables users to search for compatible partners, form work groups,
and collaborate effectively on their projects. The general nature of the Co-Partage system is that of an online
collaboration platform. It is a web-based system, accessible via a web browser, which allows users to create profiles,
search for partners, form work groups, and communicate with other users. The system is designed to be user-friendly,
intuitive, and secure, providing a smooth and seamless user experience. It also supports features such as project
management, notifications, user comments, and ratings.

## Technologies Used

- **Spring Boot**: Framework for building Java-based microservices.
- **MySQL**: Relational database management system for storing and managing data.
- **Spring Cloud**: Toolkit for building microservices-based architectures.
- **Docker/Kubernetes**: Containerization and orchestration tools for deployment.
- **Eureka**: Service discovery and registration for microservices.
- **Zuul**: API Gateway for routing requests to appropriate microservices.
- **Spring Security**: Security framework for authentication and authorization.

## Project Structure (Microservices Architecture)

```
microservices-project/
│
├── discovery-service/
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── com.example.discovery/
│   └── pom.xml
│
├── gateway-service/
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── com.example.gateway/
│   └── pom.xml
│
├── config-service/
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── com.example.config/
│   └── pom.xml
│
├── common-libraries/
│   └── src/
│       └── main/
│           └── java/
│               └── com.example.common/
│
├── service-one/
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── com.example.serviceone/
│   └── pom.xml
│
├── service-two/
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── com.example.servicetwo/
│   └── pom.xml
│
└── pom.xml
```

- **discovery-service/**: Eureka server for service discovery and registration.
- **gateway-service/**: Zuul API Gateway for routing requests to microservices.
- **config-service/**: Spring Cloud Config server for externalized configuration.
- **common-libraries/**: Common libraries shared across microservices.
- **service-one/**, **service-two/**: Sample microservices.

## Database Configuration

In each microservice's `application.properties` file, configure the database connection properties:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_name
spring.datasource.username=db_username
spring.datasource.password=db_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
```

Replace `db_name`, `db_username`, and `db_password` with your MySQL database name, username, and password.

## Running the Application

To run the entire microservices architecture locally, follow these steps:

1. Start the discovery service:
   ```bash
   cd discovery-service
   mvn spring-boot:run
   ```

2. Start the config service:
   ```bash
   cd config-service
   mvn spring-boot:run
   ```

3. Start the gateway service:
   ```bash
   cd gateway-service
   mvn spring-boot:run
   ```

4. Start each microservice individually:
   ```bash
   cd service-one
   mvn spring-boot:run
   ```

   ```bash
   cd service-two
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`.

## Endpoints

Document the available endpoints and their functionalities.

## Testing

Describe how to run unit tests and integration tests.

## CI/CD and vulnerabilities

Use the built-in continuous integration in GitLab.

- [x] [Get started with GitLab CI/CD](https://docs.gitlab.com/ee/ci/quick_start/index.html)
- [ ] [Analyze your code for known vulnerabilities with Static Application Security Testing (/SAST)](https://docs.gitlab.com/ee/user/application_security/sast/)

## Deployment

Provide instructions for deploying the microservices architecture to production environments using Docker/Kubernetes.

## Contributing

Guidelines for contributing to the project, including code style, issue reporting, and pull requests.

## License

Specify the license under which the microservices architecture project is distributed.
