package hi.place.service.impl;

import hi.place.model.user.Client;
import hi.place.model.user.User;
import hi.place.repository.user.UserRepository;
import hi.place.service.ClientService;
import hi.place.service.EmailService;
import hi.place.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private static final String CONFIRMATION_VARIABLE = "confirmationUrl";
    private static final String VERIFICATION_TEMPLATE_NAME = "confirm-email";
    private static final String SUCCESS_REVIEW_TEMPLATE_NAME = "success-review";
    private static final String EMAIL_SUBJECT = "Команда HiPlace";
    private static final String CONFIRM_ENDPOINT = "/confirm-email";

    @Value("${hi.place.base.url}")
    private String hiPlaceBaseUrl;

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ClientService clientService;

    @Override
    public void sendConfirmEmail(Long id) {
        User user = userService.getById(id);
        String token = UUID.randomUUID().toString();
        user.setVerificationToken(token);
        userRepository.save(user);
        String to = user.getEmail();
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            String confirmationUrl = createConfirmEmailUrl(token);

            Context context = new Context();
            context.setVariable(CONFIRMATION_VARIABLE, confirmationUrl);
            String htmlContent = templateEngine.process(VERIFICATION_TEMPLATE_NAME, context);

            helper.setTo(to);
            helper.setSubject(EMAIL_SUBJECT);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Can't send an email to: " + to, e);
        }
    }

    @Override
    public boolean confirmEmail(String token) {
        return userService.setEmailConfirmed(token);
    }

    @Override
    public void sendSuccessReview(Long clientId) {
        Client client = clientService.getById(clientId);
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            Context context = new Context();
            context.setVariable("clientName", client.getName());
            String htmlContent = templateEngine.process(SUCCESS_REVIEW_TEMPLATE_NAME, context);

            helper.setTo(client.getEmail());
            helper.setSubject(EMAIL_SUBJECT);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Can't send an email to: " + client.getEmail(), e);
        }
    }

    private String createConfirmEmailUrl(String token) {
        String url = hiPlaceBaseUrl + CONFIRM_ENDPOINT;
        String key = "token";
        return UriComponentsBuilder.fromUriString(url)
                .queryParam(key, token)
                .build()
                .toString();
    }
}
