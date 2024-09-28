package hi.place.controller;

import hi.place.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @GetMapping("/send/user/{userId}")
    public ResponseEntity<Boolean> sendEmail(@PathVariable Long userId) {
        emailService.sendConfirmEmail(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmEmail(@RequestParam("token") String token) {
        boolean isConfirmed = emailService.confirmEmail(token);
        return new ResponseEntity<>(isConfirmed ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
