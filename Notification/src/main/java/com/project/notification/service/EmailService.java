package com.project.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    public void sendMail(String email, String productName) {
        log.info("Mail sent to {} for product {}", email, productName);
    }
 }