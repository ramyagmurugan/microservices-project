package com.project.notification.component;

import com.project.notification.model.OrderPlacedEvent;
import com.project.notification.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class OrderEventConsumerTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private OrderEventConsumer consumer;

    @Test
    void consume_ShouldSendEmail() {

        OrderPlacedEvent event = new OrderPlacedEvent();

        event.setOrder_id(1L);
        event.setUser_id(100L);
        event.setEmail_id("test@gmail.com");
        event.setProduct_name("Laptop");

        consumer.consume(event);

        verify(emailService, times(1))
                .sendMail(
                        "test@gmail.com",
                        "Laptop");
    }
}