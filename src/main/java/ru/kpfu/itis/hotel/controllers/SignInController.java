package ru.kpfu.itis.hotel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.kpfu.itis.hotel.dto.LoginDto;
import ru.kpfu.itis.hotel.exceptions.WrongEmailOrPasswordException;
import ru.kpfu.itis.hotel.services.NewsService;
import ru.kpfu.itis.hotel.services.OAuthService;
import ru.kpfu.itis.hotel.services.OAuthServiceImpl;
import ru.kpfu.itis.hotel.services.UsersService;

import javax.annotation.security.PermitAll;

/**
 * 03.05.2022
 * SignInController
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

@Controller
@SessionAttributes(value = "Email")
public class SignInController {

    private final UsersService usersService;
    private final OAuthService oAuthService;

    @Autowired
    public SignInController(UsersService usersService, OAuthService oAuthService) {
        this.usersService = usersService;
        this.oAuthService = oAuthService;
    }

    @PermitAll
    @GetMapping(value = "/signIn")
    public String getSignInPage(Model model) {
        System.out.println("hello");
        model.addAttribute("oauthStartUri", oAuthService.getAuthorizationStartUrl());
        return "sign_in_page";
    }

    @PermitAll
    @PostMapping(value = "/signIn")
    public String signInUser(ModelMap map, LoginDto loginDto) {
        try {
            usersService.signIn(loginDto);
            //TODO: хранить в сессии объект User вместо Email
            map.put("Email", loginDto.getEmail());
            return "redirect:/main";
        } catch (WrongEmailOrPasswordException e) {
            map.put("wrongEmailOrPasswordMessage", "Неправильный логин или пароль.");
        }

        return "redirect:/signIn";
    }
}
