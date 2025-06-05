package net.infosyscap.focusField.users;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.infosyscap.focusField.jwt.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AppUser registerUser(String username, String password, String email, String nome, String cognome, Set<Role> roles) {
        if (appUserRepository.existsByUsername(username)) {
            throw new EntityExistsException("Username già in uso");
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setEmail(email);
        appUser.setNome(nome);
        appUser.setCognome(cognome);
        appUser.setRoles(roles);

        return appUserRepository.save(appUser);
    }

    public void saveVerificationCode(String email, String code) {
        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
        user.setVerificationCode(code);
        user.setVerified(false);
        appUserRepository.save(user);
    }


    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato con username: " + username));
    }

    public String authenticateUser(String username, String password) {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));

        if (!user.isVerified()) {
            throw new DisabledException("Email non verificata. Controlla la tua casella di posta.");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtTokenUtil.generateToken(user);
    }

    public AppUser findOrCreateUserByEmail(
            String email,
            String nome,
            String cognome,
            String pictureUrl,
            String providerId,
            AuthProvider provider
    ) {
        return appUserRepository.findByEmail(email).orElseGet(() -> {
            AppUser newUser = new AppUser();
            newUser.setUsername(email);
            newUser.setEmail(email);
            newUser.setNome(nome);
            newUser.setCognome(cognome);
            newUser.setPassword(null);
            newUser.setRoles(Set.of(Role.ROLE_USER));
            newUser.setVerified(true);
            newUser.setProvider(provider);
            newUser.setProviderId(providerId);
            newUser.setPictureUrl(pictureUrl);

            if (provider == AuthProvider.GOOGLE) {
                newUser.setGoogleAccount(true);
            } else if (provider == AuthProvider.FACEBOOK) {
                newUser.setFacebookAccount(true);
            }

            return appUserRepository.save(newUser);
        });
    }

}

