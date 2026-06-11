package com.project.orders.feignclient;

import com.project.orders.model.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Users")
public interface UserClient {

    @GetMapping("/users/{userId}")
    UserResponse getUser(@PathVariable("userId") Long userId);
}
