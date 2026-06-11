package com.project.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.users.entity.Users;
import com.project.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WithMockUser
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser
    void createUser() throws Exception {

        Users user = new Users();
        user.setUser_name("Ramya");
        user.setEmail_id("ramya@gmail.com");

        when(userService.createUser(user))
                .thenReturn(user);

        mockMvc.perform(post("/users/createuser")
                        .with(csrf())
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getUsers() throws Exception {
        when(userService.getAllUsers() )
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/users/getusers"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getUserById() throws Exception {

        Users user = new Users();
        user.setUser_id(1L);
        user.setUser_name("Ramya");

        when(userService.getUserById(1L))
                .thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk());
    }
}