package team.yellow.docconnect.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.webtoken.JsonWebSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import team.yellow.docconnect.exception.HealthCareAPIException;
import team.yellow.docconnect.payload.dto.GoogleUserDto;

import java.io.IOException;

@Component
public class GoogleTokenDecoder  {

    public GoogleTokenDecoder() {
    }

    public  GoogleIdToken parseGoogleIdToken(String idTokenString) throws IOException {
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        JsonWebSignature jws = JsonWebSignature.parser(jsonFactory)
                .setPayloadClass(GoogleIdToken.Payload.class)
                .parse(idTokenString);

        return new GoogleIdToken(jws.getHeader(), (GoogleIdToken.Payload) jws.getPayload(), jws.getSignatureBytes(), jws.getSignedContentBytes());
    }
    public GoogleUserDto decodeGoogleToken(String idToken) throws IOException {

        GoogleIdToken googleIdToken = parseGoogleIdToken(idToken);

        if (googleIdToken != null) {
            GoogleIdToken.Payload payload = googleIdToken.getPayload();


            String email = payload.getEmail();
            String givenName = (String) payload.get("given_name");
            String familyName = (String) payload.get("family_name");

            return new GoogleUserDto(email, givenName, familyName);
        } else {
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, "Invalid token");
        }
    }

}
