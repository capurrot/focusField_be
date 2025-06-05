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
import org.springframework.web.client.RestTemplate;

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
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest registerRequest) {
        try {
            Set<Role> roles = new HashSet<>();
            roles.add(Role.ROLE_USER);

            String verificationCode = UUID.randomUUID().toString();

            String link = "https://www.focusfield.it/verify-email?code=" + verificationCode;

            emailService.sendRegistrationEmail(
                    registerRequest.getEmail(),
                    "Verifica il tuo indirizzo email",
                    verificationCode,
                    link
            );

            AppUser newUser = appUserService.registerUser(
                    registerRequest.getUsername(),
                    registerRequest.getPassword(),
                    registerRequest.getEmail(),
                    registerRequest.getNome(),
                    registerRequest.getCognome(),
                    roles
            );

            appUserService.saveVerificationCode(newUser.getEmail(), verificationCode);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Registrazione avvenuta con successo. Controlla l’email per completare la verifica.");
            return ResponseEntity.ok(response);

        } catch (MessagingException e) {
            // Log errore e ritorna errore personalizzato
            Map<String, String> error = new HashMap<>();
            error.put("error", "Errore durante l'invio dell'email di verifica. Riprova più tardi.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
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

    @PostMapping("/facebook")
    public ResponseEntity<?> loginWithFacebook(@RequestBody Map<String, String> request) {
        String accessToken = request.get("accessToken");

        if (accessToken == null || accessToken.isBlank()) {
            return ResponseEntity.badRequest().body("AccessToken mancante");
        }

        try {
            // Richiesta al Graph API di Facebook per ottenere dati utente
            String url = "https://graph.facebook.com/me?fields=id,name,email,picture&access_token=" + accessToken;
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> fbResponse = restTemplate.getForEntity(url, Map.class);

            if (!fbResponse.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("AccessToken non valido");
            }

            Map<String, Object> fbData = fbResponse.getBody();
            String email = (String) fbData.get("email");
            String name = (String) fbData.get("name");
            String facebookId = (String) fbData.get("id");

            if (email == null) {
                return ResponseEntity.badRequest().body("Permesso email mancante. Accesso non consentito.");
            }

            // Divide il nome completo in nome e cognome
            String[] names = name.split(" ", 2);
            String nome = names.length > 0 ? names[0] : "";
            String cognome = names.length > 1 ? names[1] : "";

            // Crea o recupera l'utente Facebook
            AppUser user = appUserService.findOrCreateUserByEmail(
                    email,
                    nome,
                    cognome,
                    null,
                    facebookId,
                    AuthProvider.FACEBOOK
            );

            String jwt = jwtTokenUtil.generateToken(user);

            return ResponseEntity.ok(Map.of("token", jwt));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore durante la verifica del token Facebook");
        }
    }

}
