package net.infosyscap.focusField.users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public <List> java.util.List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
}
