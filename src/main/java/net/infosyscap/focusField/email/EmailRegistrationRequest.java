package net.infosyscap.focusField.email;

import lombok.Data;

@Data
public class EmailRegistrationRequest {
    private String to;
    private String subject;
    private String code;
    private String link;
}
