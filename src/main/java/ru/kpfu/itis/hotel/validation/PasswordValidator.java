package ru.kpfu.itis.hotel.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 03.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.trim().length() > 8 && value.matches(".*[A-Z].*") &&
                value.matches(".*[a-z].*") && value.matches(".*[0-9].*");
    }
}
