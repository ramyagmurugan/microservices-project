package com.project.orders.controller;

import com.project.orders.entity.Orders;
import com.project.orders.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@Tag(name = "Order APIs",
        description = "Operations related to Orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Operation(summary = "Place Order")
    @PostMapping("/createorder")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String placeOrder(@RequestBody Orders orders){
        return orderService.placeOrder(orders);
    }

    @Operation(summary = "Get All Orders")
    @GetMapping("/getorders")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Orders> getAllUsers(){
        return orderService.getAllOrders();
    }

    @Operation(summary = "Get Order By Id")
    @GetMapping("/id/{orderId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Optional<Orders> getUserById(@PathVariable Long orderId){
        return orderService.getUserById(orderId);
    }
}
