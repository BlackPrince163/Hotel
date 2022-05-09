package ru.kpfu.itis.hotel.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 06.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Loggable {
}
