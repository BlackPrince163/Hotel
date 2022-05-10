package ru.kpfu.itis.hotel.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.hotel.dto.UserDto;
import ru.kpfu.itis.hotel.models.User;
import ru.kpfu.itis.hotel.services.UsersService;

import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * 10.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

@RestController
public class UsersRestController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/api/users")
    @PermitAll
    public List<User> getUsers() {
        return usersService.getAllUsers();
    }

    @PostMapping("/api/users")
    public User addUser(@RequestBody UserDto user) {
        return usersService.addUser(user);
    }

    @PutMapping("/api/users/{user-id}")
    @PermitAll
    public User updateUser(@PathVariable("user-id") Long userId, @RequestBody UserDto user) {
        return usersService.updateUser(userId, user);
    }

    @DeleteMapping("/api/users/{user-id}")
    @PermitAll
    public ResponseEntity<?> deleteUser(@PathVariable("user-id") Long userId) {
        usersService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
