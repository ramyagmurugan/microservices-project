# Orders Microservice

Orders Microservice built using Spring Boot and Spring Cloud as part of a microservices architecture.

---

# Features

* Order Management APIs
* Spring Boot REST APIs
* Spring Data JPA
* MySQL Database Integration
* OAuth2 JWT Security
* Eureka Client Registration
* Kafka Integration
* Resilience4j Circuit Breaker
* Swagger/OpenAPI Documentation
* Actuator Monitoring
* Unit & Integration Testing Support

---

# Technologies Used

| Technology      | Version  |
| --------------- | -------- |
| Java            | 17       |
| Spring Boot     | 3.2.5    |
| Spring Cloud    | 2023.0.2 |
| Spring Security | Latest   |
| Spring Data JPA | Latest   |
| MySQL           | Latest   |
| Kafka           | Latest   |
| Eureka Client   | Latest   |
| Resilience4j    | Latest   |
| Swagger OpenAPI | 2.5.0    |
| Maven           | Latest   |

---

# Project Structure

```text
src
├── main
│   ├── java/com/project/orders
│   │   ├── controller
│   │   ├── service
│   │   ├── repository
│   │   ├── entity
│   │   ├── dto
│   │   ├── config
│   │   └── OrdersApplication.java
│   │
│   └── resources
│       └── application.properties
│
└── test
    └── java/com/project/orders
```

---

# Running the Application

## Build the Project

```bash
mvn clean install
```

---

## Run the Application

```bash
mvn spring-boot:run
```

Application runs on:

```text
http://localhost:8081
```

---

# Database Configuration

Configured MySQL database:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/orders
spring.datasource.username=root
spring.datasource.password=root
```

Create database manually:

```sql
CREATE DATABASE orders;
```

---

# Security Configuration

This service uses OAuth2 JWT Resource Server authentication.

JWT Issuer:

```properties
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:9000
```

Auth Server must be running before starting this service.

---

# Eureka Configuration

Service registers automatically with Eureka Server.

```properties
eureka.client.service-url.defaultZone=http://localhost:8082/eureka
```

Eureka Dashboard:

```text
http://localhost:8082
```

---

# Kafka Configuration

Kafka Broker:

```properties
spring.kafka.bootstrap-servers=localhost:9092
```

Ensure Kafka is running before using messaging features.

---

# Resilience4j Circuit Breaker

Configured for Product Service communication.

```properties
resilience4j.circuitbreaker.instances.productService.slidingWindowSize=5
resilience4j.circuitbreaker.instances.productService.failureRateThreshold=50
resilience4j.circuitbreaker.instances.productService.waitDurationInOpenState=10s
```

---

# Swagger/OpenAPI

Swagger UI:

```text
http://localhost:8081/swagger-ui.html
```

OpenAPI Docs:

```text
http://localhost:8081/v3/api-docs
```

---

# Actuator Endpoints

Actuator Base URL:

```text
http://localhost:8081/actuator
```

Health Endpoint:

```text
http://localhost:8081/actuator/health
```

Info Endpoint:

```text
http://localhost:8081/actuator/info
```

---

# Example API Request

```bash
curl --location 'http://localhost:8081/orders' \
--header 'Authorization: Bearer <jwt-token>'
```

---

# Testing

## Run All Tests

```bash
mvn test
```

---

## Test Database

Uses H2 in-memory database during tests.

```properties
spring.datasource.url=jdbc:h2:mem:testdb
```

---

# Required Services

Before running the Orders service, ensure these services are running:

| Service       | Port |
| ------------- | ---- |
| Auth Server   | 9000 |
| Eureka Server | 8082 |
| Kafka         | 9092 |
| MySQL         | 3306 |

---

# Common Issues

## 401 Unauthorized

Ensure JWT token is valid and Auth Server is running.

---

## Swagger Returns 401

Permit Swagger endpoints in Security Configuration:

---

## Kafka Connection Error

Ensure Kafka server is running on:

```text
localhost:9092
```

---

## MySQL Connection Failed

Verify:

* MySQL service is running
* Database exists
* Username/password are correct

---

# Future Enhancements

* Distributed Tracing
* API Rate Limiting
* Docker Support
* Kubernetes Deployment
* Centralized Logging
* Notification Service Integration

---

# Author

Developed using Spring Boot Microservices Architecture with Spring Cloud ecosystem.
