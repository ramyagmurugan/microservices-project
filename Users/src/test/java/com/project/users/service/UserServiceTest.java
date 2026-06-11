package com.project.users.service;

import com.project.users.entity.Users;
import com.project.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    void createUser() {

        Users user = new Users();
        user.setUser_name("Ramya");
        user.setEmail_id("ramya@gmail.com");

        when(repository.save(user)).thenReturn(user);

        Users result = service.createUser(user);

        assertEquals("Ramya", result.getUser_name());

        verify(repository, times(1)).save(user);
    }

    @Test
    void getAllUsers() {

        when(repository.findAll())
                .thenReturn(Arrays.asList(
                        new Users(),
                        new Users()));

        List<Users> users = service.getAllUsers();

        assertEquals(2, users.size());
    }

    @Test
    void getUserById() {

        Users user = new Users();
        user.setUser_id(1L);
        user.setUser_name("Ramya");

        when(repository.findById(1L))
                .thenReturn(Optional.of(user));

        Users result = service.getUserById(1L);

        assertEquals("Ramya", result.getUser_name());
    }

    @Test
    void getUserById_NotFound() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException ex =
                assertThrows(RuntimeException.class,
                        () -> service.getUserById(1L));

        assertTrue(ex.getMessage()
                .contains("not found"));
    }
}