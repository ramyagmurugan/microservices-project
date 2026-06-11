package com.project.orders.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.orders.entity.Orders;
import com.project.orders.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @Test
    void createOrder() throws Exception {

        Orders order = new Orders();
        order.setProductId(1L);
        order.setUserId(1L);
        order.setQuantity(2);

        when(orderService.placeOrder(order))
                .thenReturn("Order Created");

        mockMvc.perform(post("/orders/createorder")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllOrders() throws Exception {

        when(orderService.getAllOrders())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/orders/getorders"))
                .andExpect(status().isOk());
    }
}