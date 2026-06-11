package com.project.product.contoller;

import com.project.product.entity.Products;
import com.project.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@Tag(name = "Product APIs",
        description = "Operations related to Products")
public class ProductController {
    @Autowired
    ProductService productService;

    @Operation(summary = "Add Product")
    @PostMapping("/addProduct")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Products addProduct(@RequestBody Products products){
        return productService.add(products);
    }

    @Operation(summary = "Get All Products")
    @GetMapping("/listproducts")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Products> getProducts(){
        return productService.getProducts();
    }

    @Operation(summary = "Get Product By Id")
    @GetMapping("/{productId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Products getProductByID(@PathVariable Long productId){
        return productService.getProductById(productId);
    }
}
