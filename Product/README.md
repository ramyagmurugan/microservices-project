# Product Service

Product Service is a Spring Boot Microservice responsible for managing product information in the E-Commerce / Order Management System.

## Features

- Add Product
- Get All Products
- Get Product By ID
- Eureka Client Registration
- OAuth2 Resource Server Security
- MySQL Database Integration
- Unit Testing
- Integration Testing

## Tech Stack

- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- Spring Security OAuth2 Resource Server
- MySQL
- Eureka Discovery Server
- Maven
- JUnit 5
- Mockito
- H2 Database

## API Endpoints

### Add Product

POST /product/addProduct

Request:

```json
{
  "product_name": "Laptop",
  "price": 50000
}
```

### Get All Products

GET /product/listproducts

### Get Product By ID

GET /product/{productId}

Example:

GET /product/1

## Database

Table: products

| Column | Type |
|----------|----------|
| product_id | BIGINT |
| product_name | VARCHAR |
| price | DOUBLE |

## Running Application

```bash
mvn clean install
mvn spring-boot:run
```

## Running Tests

```bash
mvn test
```

## Test Coverage

- Service Layer Unit Tests
- Controller Layer Unit Tests
- Repository Tests
- Integration Tests

## Author

Product Microservice

## Swagger and actuator
http://localhost:8080/swagger-ui.html
http://localhost:8080/v3/api-docs
http://localhost:8080/actuator
http://localhost:8080/actuator/health