package ru.kpfu.itis.hotel.exceptions;

/**
 * 03.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

public class DuplicateEntryException extends HotelApplicationException {

    public static final String DEFAULT_MESSAGE = "Duplicate Entry Exception";

    public DuplicateEntryException() {
        super(DEFAULT_MESSAGE);
    }

    public DuplicateEntryException(String message) {
        super(message);
    }
}
