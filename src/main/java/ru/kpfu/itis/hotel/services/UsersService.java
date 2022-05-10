package ru.kpfu.itis.hotel.services;

import ru.kpfu.itis.hotel.dto.LoginDto;
import ru.kpfu.itis.hotel.dto.UserDto;
import ru.kpfu.itis.hotel.exceptions.DuplicateEntryException;
import ru.kpfu.itis.hotel.exceptions.WrongEmailOrPasswordException;
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

public interface UsersService {
    void save(User entity);
    void delete(User entity);
    void deleteByEmail(String email);

    void updateFirstNameAndLastNameByEmail(String newFirstName, String newLastName, String email);
    List<User> getAllUsers();
    Optional<User> findById(Long id);
    Optional<User> findOneByEmail(String email);

    User addUser(UserDto user);

    void signUp(UserDto userDto) throws DuplicateEntryException;
    void signIn(LoginDto loginDto) throws WrongEmailOrPasswordException;

    User updateUser(Long userId, UserDto user);

    void deleteUser(Long userId);
}
