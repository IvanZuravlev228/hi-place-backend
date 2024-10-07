package hi.place.service;

public interface EmailService {
    void sendConfirmEmail(Long userId);

    boolean confirmEmail(String token);

    void sendSuccessReview(Long clientId);
}
