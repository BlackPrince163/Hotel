package ru.kpfu.itis.hotel.services;

import ru.kpfu.itis.hotel.models.User;

/**
 * 09.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

public interface OAuthService {
    User vkAuth(String code);
}
