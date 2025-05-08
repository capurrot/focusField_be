package net.infosyscap.focusField.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.infosyscap.focusField.jwt.JwtTokenUtil;
import net.infosyscap.focusField.users.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/focus-field/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AppUserService appUserService;
    private final JwtTokenUtil jwtTokenUtil;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current-user")
    public AppUser getCurrentUser(@AuthenticationPrincipal AppUser appUser) {
        return appUser;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest registerRequest) {
        appUserService.registerUser(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getEmail(),
                registerRequest.getNome(),
                registerRequest.getCognome(),
                Set.of(Role.ROLE_USER)
        );

        Map<String, String> response = new HashMap<>();
        response.put("message", "Registrazione avvenuta con successo");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        log.info("Login request:");
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

        // Trova o crea l'utente
        AppUser user = appUserService.findOrCreateUserByEmail(email, nome, cognome, googleUser.getPicture(), googleUser.getGoogleId());
        String jwt = jwtTokenUtil.generateToken(user);

        return ResponseEntity.ok(Map.of("token", jwt));
    }


}
