package com.project.orders.feignclient;

import com.project.orders.model.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Product")
public interface ProductClient {
    @GetMapping("/product/{productId}")
    ProductResponse getProduct(@PathVariable("productId") Long productId);
}
