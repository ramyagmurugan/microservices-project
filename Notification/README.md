# Notification Service

## Overview

Notification Service listens to Kafka events published by the Order Service and sends notifications to users.

The service consumes messages from the Kafka topic and processes order notifications.

---

## Features

- Kafka Consumer
- Order Event Processing
- Email Notification Logging
- Eureka Client Registration
- Unit Testing
- Integration Testing

---

## Technology Stack

- Java 17
- Spring Boot 3.2.5
- Apache Kafka
- Spring Kafka
- Eureka Discovery Client
- Lombok
- Maven
- JUnit 5
- Mockito

---

## Project Structure

src
├── component
│   └── OrderEventConsumer
├── model
│   └── OrderPlacedEvent
├── service
│   └── EmailService
└── test

---

## Kafka Flow

Order Service
|
v
Kafka Topic (order-topic)
|
v
Notification Service
|
v
Email Notification

---

## Kafka Listener

Topic:

order-topic

Group:

notification-group

---

## Running Application

```bash
mvn clean install
mvn spring-boot:run
```

---

## Running Tests

```bash
mvn test
```

---

## Unit Tests

- EmailServiceTest
- OrderEventConsumerTest
- OrderPlacedEventTest

---

## Integration Tests

- NotificationApplicationTests

---

## Eureka Registration

The service registers itself with Eureka Server for service discovery.

---

## Future Enhancements

- SMTP Email Integration
- SMS Notifications
- Push Notifications
- Retry Mechanism
- Dead Letter Queue (DLQ)

---

## Author

Notification Microservice

