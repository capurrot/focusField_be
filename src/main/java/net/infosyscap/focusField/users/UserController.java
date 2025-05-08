package net.infosyscap.focusField.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/focus-field/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public java.util.List<AppUser> getAllUsers() {
        return userService.getAllUsers();
    }
}

