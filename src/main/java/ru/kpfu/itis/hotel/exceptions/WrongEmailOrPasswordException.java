package ru.kpfu.itis.hotel.exceptions;

/**
 * 03.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

public class WrongEmailOrPasswordException extends HotelApplicationException {
    public static final String DEFAULT_MESSAGE = "Wrong Email Or Password Exception";

    public WrongEmailOrPasswordException() {
        super(DEFAULT_MESSAGE);
    }

    public WrongEmailOrPasswordException(String message) {
        super(message);
    }
}
