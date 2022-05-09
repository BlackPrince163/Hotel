package ru.kpfu.itis.hotel.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kpfu.itis.hotel.exceptions.HotelApplicationException;

/**
 * 06.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String postException() {
        return "exception_page";
    }

    @ExceptionHandler(RuntimeException.class)
    public void postRuntimeException(HotelApplicationException exception) {
        log.error(exception.toString());
        log.error(exception.getMessage());
    }
}
