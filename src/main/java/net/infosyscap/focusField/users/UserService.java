package net.infosyscap.focusField.users;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public <List> java.util.List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    @Transactional
    public void updateProfileImage(String username, String imageUrl) {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
        user.setPictureUrl(imageUrl);
        userRepository.save(user);
    }


}
