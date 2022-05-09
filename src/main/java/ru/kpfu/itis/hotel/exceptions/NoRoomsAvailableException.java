package ru.kpfu.itis.hotel.exceptions;

/**
 * 06.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

public class NoRoomsAvailableException extends HotelApplicationException {
    public static final String DEFAULT_MESSAGE = "No Rooms Available Exception";

    public NoRoomsAvailableException() {
        super(DEFAULT_MESSAGE);
    }

    public NoRoomsAvailableException(String message) {
        super(message);
    }
}
