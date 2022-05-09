package ru.kpfu.itis.hotel.security.ouath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * 09.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

@Component
public class VkOAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    @Autowired
    public VkOAuthenticationProvider(@Qualifier("userDetailsVkOAuthService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        VkOAuthAuthentication vkAuthentication = (VkOAuthAuthentication) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername(vkAuthentication.getName());
        vkAuthentication.setAuthenticated(true);
        vkAuthentication.setUserDetails(userDetails);
        return vkAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return VkOAuthAuthentication.class.equals(authentication);
    }
}
