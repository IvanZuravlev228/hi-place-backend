package hi.place.service;

import hi.place.model.user.User;

public interface AuthenticationService {
    User register(User user);

    User updateData(User user);

    User login(String login, String password) throws RuntimeException;
}