package ru.kpfu.itis.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.hotel.models.User;

import java.util.List;
import java.util.Optional;

/**
 * 03.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findAllByIsDeletedIsNull();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM user_hotel WHERE email = :email")
    void deleteByEmail(String email);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE user_hotel " +
            "SET first_name = :newFirstName, last_name = :newLastName WHERE email = :email")
    void updateFirstNameAndLastNameByEmail(@Param("newFirstName") String newFirstName,
                                           @Param("newLastName") String newLastName,
                                           @Param("email") String email);

    @Query(nativeQuery = true, value = "SELECT * FROM user_hotel WHERE email = :email")
    Optional<User> findOneByEmail(@Param("email") String email);

}
