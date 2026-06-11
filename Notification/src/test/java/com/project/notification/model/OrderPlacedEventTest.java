package com.project.notification.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderPlacedEventTest {

    @Test
    void getterSetterTest() {

        OrderPlacedEvent event =
                new OrderPlacedEvent();

        event.setOrder_id(1L);
        event.setUser_id(2L);
        event.setEmail_id("abc@gmail.com");
        event.setProduct_name("Mobile");

        assertEquals(1L, event.getOrder_id());
        assertEquals(2L, event.getUser_id());
        assertEquals("abc@gmail.com",
                event.getEmail_id());
        assertEquals("Mobile",
                event.getProduct_name());
    }
}