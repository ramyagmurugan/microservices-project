package com.project.product.service;

import com.project.product.entity.Products;
import com.project.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Products add(Products products) {
        return productRepository.save(products);
    }

    public List<Products> getProducts(){
        return productRepository.findAll();
    }

    public Products getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() ->
                new RuntimeException("Product not found with id: " + productId));
    }
}
