package com.project.product.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.product.entity.Products;
import com.project.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(ProductController.class)
@WithMockUser
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ProductService productService;

    @Test
    @WithMockUser(roles = "USER")
    void addProduct() throws Exception {

        Products product = new Products();
        product.setProduct_name("Laptop");
        product.setPrice(50000.0);

        when(productService.add(product))
                .thenReturn(product);

        mockMvc.perform(post("/product/addProduct")
                        .with(csrf())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getProducts() throws Exception {
        when(productService.getProducts())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/product/listproducts"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getProductById() throws Exception {

        Products product = new Products();
        product.setProduct_id(1L);
        product.setProduct_name("Laptop");

        when(productService.getProductById(1L))
                .thenReturn(product);

        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk());
    }
}