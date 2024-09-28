package hi.place.service.impl;

import java.util.NoSuchElementException;

import hi.place.exception.EmailAlreadyExistsException;
import hi.place.exception.InvalidCredentialsException;
import hi.place.model.user.User;
import hi.place.repository.user.UserRepository;
import hi.place.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new EmailAlreadyExistsException("Email already exists", e);
        }
    }

    @Override
    public User updateData(User user) {
        return userRepository.save(user);
    }

    @Override
    public User login(String login, String password) throws RuntimeException {
        User user = userRepository.getUserByEmail(login).orElseThrow(() ->
                new NoSuchElementException("Can't find user by login: " + login));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Incorrect username or password");
        }
        return user;
    }
}