
# AuthServer

Spring Boot OAuth2 Authorization Server implementation using Spring Authorization Server.

## Features

- OAuth2 Authorization Server
- JWT Token generation
- OpenID Connect support
- Client Credentials grant type
- RSA key generation
- Spring Security integration
- Unit and Integration testing

## Technologies Used

- Java 17
- Spring Boot 3
- Spring Security
- Spring Authorization Server
- JUnit 5
- Mockito
- MockMvc

## Project Structure

```text
src
├── main
│   ├── java/com/project/authserver
│   └── resources
└── test
    └── java/com/project/authserver
```

## Running the Application

```bash
mvn spring-boot:run
```

Application URL:

```text
http://localhost:9000
```

## Running Tests

Run all tests:

```bash
mvn test
```

Run only unit tests:

```bash
mvn -Dtest=SecurityConfigUnitTest test
```

Run only integration tests:

```bash
mvn -Dtest=AuthServerIntegrationTest test
```

## OAuth Client Details

| Property | Value |
|---|---|
| Client ID | order-client |
| Client Secret | secret |
| Grant Type | client_credentials |
| Scope | read, write |

## OAuth Token API

```bash
curl --location 'http://localhost:9000/oauth2/token' \
--user order-client:secret \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'scope=read'
```

## Test Coverage

### Unit Tests

- Registered client configuration
- JWT customization
- JWK source creation
- Authorization server settings

### Integration Tests

- OpenID configuration endpoint
- JWK endpoint
- OAuth token endpoint

## Notes

Ensure all required dependencies are installed before running the application.

Run:

```bash
mvn clean install
```
## Swagger and actuator
http://localhost:9000/swagger-ui.html
http://localhost:9000/v3/api-docs
http://localhost:9000/actuator
http://localhost:9000/actuator/health