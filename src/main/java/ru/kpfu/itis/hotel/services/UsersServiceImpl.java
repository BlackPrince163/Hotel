package ru.kpfu.itis.hotel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.hotel.dto.LoginDto;
import ru.kpfu.itis.hotel.dto.UserDto;
import ru.kpfu.itis.hotel.exceptions.DuplicateEntryException;
import ru.kpfu.itis.hotel.exceptions.WrongEmailOrPasswordException;
import ru.kpfu.itis.hotel.models.User;
import ru.kpfu.itis.hotel.repositories.UsersRepository;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Optional;

/**
 * 03.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

@Service(value = "usersService")
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder encoder;
    private final OAuthService oAuthService;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, OAuthService oAuthService) {
        this.usersRepository = usersRepository;
        this.oAuthService = oAuthService;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public void save(User entity) {
        usersRepository.save(entity);
    }

    public User getUser() throws IOException {
        URL url = null;
        url = new URL(oAuthService.getAuthorizationStartUrl());
        HttpURLConnection httpConnection = null;
        httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestMethod("GET");
        httpConnection.setRequestProperty("authority", "oauth.vk.com");
        httpConnection.setRequestProperty("accept", "text/html, application/xhtml+xml, application/xm1; q=0.9");
        httpConnection.setRequestProperty("accept-language", "ru-RU,ru");
        httpConnection.setRequestProperty("cache-control", "max-age=0");


        StringBuilder content;

        try (BufferedReader input = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()))) {
            String line;
            content = new StringBuilder();
            while ((line = input.readLine()) != null) {
                // Append each line of the response and separate them
                content.append(line);
                content.append(System.lineSeparator());
            }
        } finally {
            httpConnection.disconnect();
        }
        String token = httpConnection.getContentType();
        User user = new User();
        // Output the content to the console
        System.out.println(content.toString());
        return user;
    }

    @Override
    public User addUser(UserDto user) {
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(User.Role.USER_ROLE)
                .state(User.State.ACTIVE_STATE)
                .build();
        usersRepository.save(newUser);
        return newUser;
    }


    @Override
    public void delete(User entity) {
        usersRepository.delete(entity);
    }

    @Override
    public void deleteByEmail(String email) {
        usersRepository.deleteByEmail(email);
    }

    /*@Override
    public void update(User entity) {
        usersRepository.update(entity);
    }*/

    @Override
    public void updateFirstNameAndLastNameByEmail(String newFirstName, String newLastName, String email) {
        usersRepository.updateFirstNameAndLastNameByEmail(newFirstName, newLastName, email);
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return usersRepository.findOneByEmail(email);
    }

    @Override
    public void signUp(UserDto userDto) throws DuplicateEntryException {
        Optional<User> userOptional = findOneByEmail(userDto.getEmail());
        // Разрешаем регистрацию, если данных нового пользователя нет в БД.
        if (!userOptional.isPresent()) {
            User user = User.builder()
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .email(userDto.getEmail())
                    .role(User.Role.USER_ROLE)
                    .state(User.State.ACTIVE_STATE)
                    .hashPassword(encoder.encode(userDto.getPassword()))
                    .build();
            save(user);
        } else throw new DuplicateEntryException("Пользователь с таким email уже существует.");
    }

    @Override
    public void signIn(LoginDto loginDto) throws WrongEmailOrPasswordException {
        Optional<User> userOptional = findOneByEmail(loginDto.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!encoder.matches(loginDto.getPassword(), user.getHashPassword())) {
                throw new WrongEmailOrPasswordException();
            }
        } else throw new WrongEmailOrPasswordException();
    }

    @Override
    public User updateUser(Long userId, UserDto user) {
        User userForUpdate = usersRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        userForUpdate.setFirstName(user.getFirstName());
        userForUpdate.setLastName(user.getLastName());
        usersRepository.save(userForUpdate);
        return userForUpdate;
    }

    @Override
    public void deleteUser(Long userId) {
        User userForDelete = usersRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        usersRepository.delete(userForDelete);
    }
}
