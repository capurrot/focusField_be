package net.infosyscap.focusField.email;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    EmailService emailService;
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void sendEmail(@RequestBody EmailRegistrationRequest request) throws MessagingException {
        emailService.sendRegistrationEmail(request.getTo(), request.getSubject(), request.getCode(), request.getLink());
    }
}

