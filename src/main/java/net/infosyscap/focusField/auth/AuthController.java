package net.infosyscap.focusField.auth;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.infosyscap.focusField.email.EmailService;
import net.infosyscap.focusField.jwt.JwtTokenUtil;
import net.infosyscap.focusField.users.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/focus-field/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;
    private final EmailService emailService;
    private final JwtTokenUtil jwtTokenUtil;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current-user")
    public AppUser getCurrentUser(@AuthenticationPrincipal AppUser appUser) {
        return appUser;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest registerRequest) throws MessagingException {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);

        AppUser newUser = appUserService.registerUser(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getEmail(),
                registerRequest.getNome(),
                registerRequest.getCognome(),
                roles
        );

        // Genera codice di verifica
        String verificationCode = UUID.randomUUID().toString();
        appUserService.saveVerificationCode(newUser.getEmail(), verificationCode);

        // Costruisci il link di verifica
        String link = "http://192.168.178.111:8080/api/focus-field/auth/verify-email?code=" + verificationCode;

        // Invia email HTML da template
        emailService.sendRegistrationEmail(
                newUser.getEmail(),
                "Verifica il tuo indirizzo email",
                verificationCode,
                link
        );

        Map<String, String> response = new HashMap<>();
        response.put("message", "Registrazione avvenuta con successo. Controlla l’email per completare la verifica.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam String code) {
        Optional<AppUser> optionalUser = appUserRepository.findByVerificationCode(code);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Codice di verifica non valido");
        }

        AppUser user = optionalUser.get();
        user.setVerified(true);
        user.setVerificationCode(null);
        appUserRepository.save(user);

        return ResponseEntity.ok("Email verificata con successo! Ora puoi accedere.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        log.info("Login attempt for user: {}", loginRequest.getUsername());

        Optional<AppUser> optionalUser = appUserService.findByUsername(loginRequest.getUsername());

        if (optionalUser.isEmpty() || !optionalUser.get().isVerified()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        AppUser user = optionalUser.get();

        String token = appUserService.authenticateUser(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody GoogleUserResponse googleUser) {
        String email = googleUser.getEmail();
        String nome = googleUser.getFirstName();
        String cognome = googleUser.getLastName();

        AppUser user = appUserService.findOrCreateUserByEmail(email, nome, cognome, googleUser.getPicture(), googleUser.getGoogleId(), AuthProvider.GOOGLE );
        String jwt = jwtTokenUtil.generateToken(user);

        return ResponseEntity.ok(Map.of("token", jwt));
    }
}
