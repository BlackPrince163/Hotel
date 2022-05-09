package ru.kpfu.itis.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.hotel.models.Room;

import java.util.List;
import java.util.Optional;

/**
 * 06.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

public interface RoomsRepository extends JpaRepository<Room, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM room_hotel where name = :name")
    Optional<Room> findByName(String name);

    List<Room> findAllByDateToLessThan(Long dateFrom);

    @Query(nativeQuery = true, value =
            "WITH _popular_rooms AS (\n" +
                    "    SELECT bh.room_id\n" +
                    "    FROM booking_hotel bh\n" +
                    "    GROUP BY bh.room_id\n" +
                    "    HAVING bh.room_id = MAX(bh.room_id)\n" +
                    ")\n" +
                    "SELECT rh.id, rh.adults_number, rh.child_number, rh.date_from, rh.date_to,\n" +
                    "       rh.name, rh.photo, rh.price, rh.rooms_number, rh.user_id\n" +
                    "FROM room_hotel rh\n" +
                    "         INNER JOIN _popular_rooms _pr ON rh.id = _pr.room_id")
    List<Room> findMostPopularRooms();
}
