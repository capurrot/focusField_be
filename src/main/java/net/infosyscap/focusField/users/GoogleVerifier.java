package net.infosyscap.focusField.users;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import org.springframework.stereotype.Component;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Collections;

@Component
public class GoogleVerifier {

    private final GoogleIdTokenVerifier verifier;

    public GoogleVerifier() {
        try {
            this.verifier = new GoogleIdTokenVerifier.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance()
            )
                    .setAudience(Collections.singletonList("870727527546-k6lbah5k54i71lqmbc5m7v00bnm14en4.apps.googleusercontent.com"))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Errore nella configurazione di GoogleVerifier", e);
        }
    }

    public GoogleIdToken.Payload verify(String idTokenString) {
        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);
            return idToken != null ? idToken.getPayload() : null;
        } catch (Exception e) {
            return null;
        }
    }
}
