package com.project.notification.component;

import com.project.notification.model.OrderPlacedEvent;
import com.project.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventConsumer {
   private final EmailService emailService;
    @KafkaListener(
                topics = "order-topic",
                groupId = "notification-group")
        public void consume(OrderPlacedEvent event) {

            log.info("Received Order : "
                            + event.getOrder_id());

            emailService.sendMail(
                    event.getEmail_id(),
                    event.getProduct_name());
        }
    }

