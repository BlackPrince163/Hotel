package ru.kpfu.itis.hotel.services;

import ru.kpfu.itis.hotel.models.BookingHistory;
import ru.kpfu.itis.hotel.models.Room;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 06.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

public interface BookingHistoryService {
    void save(BookingHistory entity);

    void delete(BookingHistory entity);

    List<BookingHistory> getAllBookingHistory();
    List<Room> findRoomsByUser(Long userId, Date dateFrom);
    Optional<BookingHistory> findByRoomIdAndUserId(Long roomId, Long userId);
    List<BookingHistory> findAllByUserId(Long userId);

    void addBooking(Long userId, Long roomId, Date dateFrom, Date dateTo);
    BookingHistory editBooking(Long userId, Long roomId, Date dateFrom, Date dateTo);

    void deleteById(Long bookingId);
}
