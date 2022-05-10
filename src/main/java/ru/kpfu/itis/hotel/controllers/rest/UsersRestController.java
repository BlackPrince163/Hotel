package ru.kpfu.itis.hotel.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.hotel.models.User;
import ru.kpfu.itis.hotel.services.UsersService;

import java.util.List;

/**
 * 10.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

@Controller
public class UsersRestController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/api/users")
    @ResponseBody
    public List<User> getUsers() {
        return usersService.getAllUsers();
    }
}
