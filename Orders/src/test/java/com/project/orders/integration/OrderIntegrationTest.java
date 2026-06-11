package com.project.orders.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.orders.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void applicationLoads() throws Exception {

        mockMvc.perform(get("/orders/getorders"))
                .andExpect(status().isOk());
    }
}