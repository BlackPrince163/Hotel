package ru.kpfu.itis.hotel.controllers.OAuth;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 09.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

@Controller
@RequestMapping("/auth")
public class VkOAuthController {

    @GetMapping("/vk")
    public String socialVk(@Param("code") String code, HttpServletRequest request) {
        return "redirect:/main";
    }
}
