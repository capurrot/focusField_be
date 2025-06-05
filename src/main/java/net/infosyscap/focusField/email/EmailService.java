package net.infosyscap.focusField.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(String to, String subject, String code, String link) throws MessagingException {
        String html = loadTemplate("templates/registration-email.html")
                .replace("{{CODE}}", code)
                .replace("{{LINK}}", link);

        sendHtml(to, subject, html);
    }

    public void sendHtml(String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true); // true = HTML

        helper.setFrom("noreply@focusfield.it");

        mailSender.send(message);
        System.out.println("Email HTML inviata con successo a " + to);
    }

    private String loadTemplate(String path) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Template non trovato: " + path);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Errore nella lettura del template: " + path, e);
        }
    }

    public void sendEmailWithAttachment(String to, String subject, String body, byte[] attachmentBytes, String attachmentName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String[] recipients = to.split("\\s*[,;]\\s*");
        helper.setTo(recipients);

        helper.setSubject(subject);
        helper.setText(body, true);
        helper.addAttachment(attachmentName, new ByteArrayResource(attachmentBytes));
        mailSender.send(message);
        System.out.println("Email inviata con successo a " + to + " con allegato: " + attachmentName);
    }
}
