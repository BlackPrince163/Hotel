package ru.kpfu.itis.hotel.exceptions;

/**
 * 03.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

public class HotelApplicationException extends RuntimeException {
    public HotelApplicationException(String message) {
        super(message);
    }
}
