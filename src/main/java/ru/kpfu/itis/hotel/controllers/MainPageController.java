package ru.kpfu.itis.hotel.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServlet;

/**
 * 06.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

@Controller
public class MainPageController extends HttpServlet {

    @GetMapping("/main")
    @PermitAll
    public String getMainPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("isAuthenticated", userDetails != null);
        return "main_page";
    }
}
