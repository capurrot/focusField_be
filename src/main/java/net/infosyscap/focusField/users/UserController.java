package net.infosyscap.focusField.users;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/focus-field/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final Cloudinary cloudinary;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public java.util.List<AppUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("username")
    public boolean getUserByUsername(String username) {
        return userService.existsByUsername(username);
    }
    @GetMapping("email")
    public boolean getUserByEmail(String email) {
        return userService.existsByEmail(email);
    }
    @PostMapping("/upload-image")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> uploadProfileImage(
            @RequestParam("image") MultipartFile image,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        try {
            if (image.isEmpty()) {
                return ResponseEntity.badRequest().body("File vuoto");
            }

            Map<?, ?> uploadResult = cloudinary.uploader().upload(image.getBytes(), Map.of());
            String imageUrl = (String) uploadResult.get("secure_url");

            // Salva l'URL nel database dell'utente
            userService.updateProfileImage(userDetails.getUsername(), imageUrl);

            return ResponseEntity.ok(Map.of("newImageUrl", imageUrl));
        } catch (IOException e) {
            log.error("Errore durante l'upload su Cloudinary", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore durante l'upload");
        }
    }
}

