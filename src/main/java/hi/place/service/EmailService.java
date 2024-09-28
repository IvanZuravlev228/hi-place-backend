package hi.place.service;

public interface EmailService {
    void sendConfirmEmail(Long id);

    boolean confirmEmail(String token);
}
