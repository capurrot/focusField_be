package net.infosyscap.focusField.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleUserResponse {
    private String email;
    private String firstName;
    private String lastName;
    private String googleId;
    private String picture;
}
