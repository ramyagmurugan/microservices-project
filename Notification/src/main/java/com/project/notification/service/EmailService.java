package com.project.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;
@Slf4j
@Service
public class EmailService {

    public void sendMail(String email, String productName) {
        log.info("Mail sent to {} for product {}", email, productName);
    }
 }