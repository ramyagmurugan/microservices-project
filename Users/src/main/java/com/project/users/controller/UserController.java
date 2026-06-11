package com.project.users.controller;

import com.project.users.entity.Users;
import com.project.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User APIs",
        description = "Operations related to Users")
public class UserController {
    @Autowired
    UserService userService;

    @Operation(summary = "Create User")
    @PostMapping("/createuser")
    public Users createUser(@RequestBody Users user){
        return userService.createUser(user);
    }

    @Operation(summary = "Get All Users")
    @GetMapping("/getusers")
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @Operation(summary = "Get User By Id")
    @GetMapping("/{userId}")
    public Users getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }
}
