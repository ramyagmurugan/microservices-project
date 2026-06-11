
# API Gateway

Spring Cloud Gateway implementation for microservices routing and security.

## Features

- Spring Cloud Gateway
- OAuth2 Resource Server
- JWT validation
- Route forwarding
- Centralized authentication
- Load balancing support
- Unit and Integration tests

## Technologies Used

- Java 17
- Spring Boot 3
- Spring Cloud Gateway
- Spring Security
- OAuth2 Resource Server
- JUnit 5
- WebTestClient

## Project Structure

```text
src
├── main
│   ├── java/com/project/apigateway
│   └── resources
└── test
    └── java/com/project/apigateway
```

## Running the Application

```bash
mvn spring-boot:run
```

Default application URL:

```text
http://localhost:8080
```

## Running Tests

Run all tests:

```bash
mvn test
```

Run only unit tests:

```bash
mvn -Dtest=GatewayConfigUnitTest test
```

Run only integration tests:

```bash
mvn -Dtest=ApiGatewayIntegrationTest test
```

## Integration Test Coverage

The integration tests validate:

- Gateway health endpoint
- Unauthorized access protection
- Gateway startup validation

## Unit Test Coverage

The unit tests validate:

- Route locator creation
- Gateway configuration loading

## API Gateway Responsibilities

- Request routing
- Authentication validation
- JWT token verification
- Service abstraction
- Security enforcement

## Example Protected API Request

```bash
curl --location 'http://localhost:8080/orders' \
--header 'Authorization: Bearer <jwt-token>'
```

## Notes

Ensure the Auth Server is running before testing authenticated routes.

Build the project using:

```bash
mvn clean install
```

## Swagger and actuator
http://localhost:8085/swagger-ui.html
http://localhost:8085/v3/api-docs
http://localhost:8085/actuator
http://localhost:8085/actuator/health

