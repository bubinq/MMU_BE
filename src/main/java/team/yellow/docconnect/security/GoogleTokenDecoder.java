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

//    private static final String GOOGLE_CLIENT_ID = "100732516280-3uqf9q6kfjcd22688age9gf2l8ju43hg.apps.googleusercontent.com";



    public GoogleTokenDecoder() {
    }

    public  GoogleIdToken parseGoogleIdToken(String idTokenString) throws IOException {
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance(); // Use JacksonFactory for JSON parsing

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


            System.out.println("Email: " + email);
            System.out.println("Given Name: " + givenName);
            System.out.println("Family Name: " + familyName);
            return new GoogleUserDto(email, givenName, familyName);
        } else {
            System.out.println("No token");
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, "Invalid token");
        }
    }

}
