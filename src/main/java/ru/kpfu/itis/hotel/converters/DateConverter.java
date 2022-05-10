package ru.kpfu.itis.hotel.converters;

import org.springframework.core.convert.converter.Converter;

import org.jetbrains.annotations.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 06.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

public class DateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(@NotNull String source) {
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException(exception);
        }
    }
}
