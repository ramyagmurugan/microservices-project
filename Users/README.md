# User Service

## Overview

User Service is a Spring Boot Microservice responsible for managing user information within the Order Management System.

---

## Features

- Create User
- Get All Users
- Get User By ID
- MySQL Integration
- Eureka Registration
- OAuth2 Resource Server Security
- Unit Testing
- Integration Testing

---

## Technology Stack

- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- Spring Security OAuth2 Resource Server
- MySQL
- Eureka Discovery Client
- Maven
- JUnit 5
- Mockito
- H2 Database (Testing)

---

## Project Structure

src
├── controller
│ └── UserController
├── service
│ └── UserService
├── repository
│ └── UserRepository
├── entity
│ └── Users
└── test

---

## API Endpoints

### Create User

POST /users/createuser

Request

```json
{
  "user_name": "Ramya",
  "email_id": "ramya@gmail.com"
}
```

### Get All Users

GET /users/getusers

### Get User By ID

GET /users/{userId}

Example:

GET /users/1

---

## Database

Table Name: users

| Column | Type |
|----------|----------|
| user_id | BIGINT |
| user_name | VARCHAR |
| email_id | VARCHAR |

---

## Running Application

```bash
mvn clean install
mvn spring-boot:run
```

## Running Tests

```bash
mvn test
```

---

## Test Coverage

- UserServiceTest
- UserControllerTest
- UserRepositoryTest
- UserIntegrationTest

---

## Eureka Registration

The service automatically registers with Eureka Server.

---

## Author

User Microservice

## Swagger and actuator

http://localhost:8083/swagger-ui.html
http://localhost:8083/v3/api-docs
http://localhost:8083/actuator
http://localhost:8083/actuator/health