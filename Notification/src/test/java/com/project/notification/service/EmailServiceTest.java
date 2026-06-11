package com.project.notification.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EmailServiceTest {

    private final EmailService emailService =
            new EmailService();

    @Test
    void sendMail_ShouldExecuteSuccessfully() {

        assertDoesNotThrow(() ->
                emailService.sendMail(
                        "test@gmail.com",
                        "Laptop"));
    }
}