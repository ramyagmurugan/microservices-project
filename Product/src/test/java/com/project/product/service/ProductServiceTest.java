package com.project.product.service;


import com.project.product.entity.Products;
import com.project.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @Test
    void addProduct() {

        Products product = new Products();
        product.setProduct_name("Laptop");
        product.setPrice(50000.0);

        when(repository.save(product)).thenReturn(product);

        Products result = service.add(product);

        assertEquals("Laptop", result.getProduct_name());
        verify(repository, times(1)).save(product);
    }

    @Test
    void getProducts() {

        List<Products> products = Arrays.asList(
                new Products(),
                new Products());

        when(repository.findAll()).thenReturn(products);

        List<Products> result = service.getProducts();

        assertEquals(2, result.size());
    }

    @Test
    void getProductById() {

        Products product = new Products();
        product.setProduct_id(1L);
        product.setProduct_name("Mobile");

        when(repository.findById(1L))
                .thenReturn(Optional.of(product));

        Products result = service.getProductById(1L);

        assertEquals("Mobile", result.getProduct_name());
    }

    @Test
    void getProductById_NotFound() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException ex =
                assertThrows(RuntimeException.class,
                        () -> service.getProductById(1L));

        assertEquals(
                "Product not found with id: 1",
                ex.getMessage());
    }
}