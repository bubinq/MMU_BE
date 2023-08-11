package team.yellow.docconnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import team.yellow.docconnect.payload.dto.LoginDto;
import team.yellow.docconnect.payload.dto.RegisterDto;
import team.yellow.docconnect.payload.response.JWTAuthenticationResponse;
import team.yellow.docconnect.service.AuthenticationService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@CrossOrigin(maxAge = 999999999)
@RequestMapping("api/v1/auth")
@Tag(name = "Login and Register REST APIs for Authentication Resource")
public class AuthenticationController {

    private final AuthenticationService authService;
    private final ClientRegistrationRepository clientRegistrationRepository;

    public AuthenticationController(AuthenticationService authService, ClientRegistrationRepository clientRegistrationRepository) {
        this.authService = authService;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Operation(
            summary = "Login User REST API",
            description = "Login User REST API is used to get user's bearer token"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthenticationResponse> login(@Valid @RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JWTAuthenticationResponse response = new JWTAuthenticationResponse();
        response.setAccessToken(token);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Google Login User REST API",
            description = "Google Login User REST API is used to redirect user to google login"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/google_login")
    public ResponseEntity<JWTAuthenticationResponse> googleLogin(@RequestParam(name = "access_token") String idToken) throws IOException {
        String token = authService.googleSignIn(idToken);
        JWTAuthenticationResponse response = new JWTAuthenticationResponse();
        response.setAccessToken(token);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/initiate-google")
    public ResponseEntity<String> initiateGoogleOAuth() {
        ClientRegistration googleRegistration = clientRegistrationRepository.findByRegistrationId("google");

        String authorizationUri = googleRegistration.getProviderDetails().getAuthorizationUri();
        String redirectUri = UriComponentsBuilder.fromHttpUrl(googleRegistration.getRedirectUri()).build().encode().toUriString();
        String clientId = googleRegistration.getClientId();
//        String scopes = String.join(" ", googleRegistration.getScopes());
        String scopes = "openid profile email";
        String authorizationUrl = UriComponentsBuilder.fromHttpUrl(authorizationUri)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("prompt", "consent")
                .queryParam("response_type", "code")
                .queryParam("client_id", clientId)
                .queryParam("scope", scopes)
                .build().encode().toUriString();
        return ResponseEntity.ok(authorizationUrl);
    }

    @GetMapping("/login/oauth2/code/google")
    public ResponseEntity<String> handleGoogleCallback(@RequestParam String code) {
        RestTemplate restTemplate = new RestTemplate();

        ClientRegistration googleRegistration = clientRegistrationRepository.findByRegistrationId("google");
        if(googleRegistration != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBasicAuth(googleRegistration.getClientId(), googleRegistration.getClientSecret());

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put(OAuth2ParameterNames.GRANT_TYPE, "authorization_code");
            requestBody.put(OAuth2ParameterNames.CODE, code);
            requestBody.put(OAuth2ParameterNames.REDIRECT_URI, googleRegistration.getRedirectUri());
            requestBody.put(OAuth2ParameterNames.CLIENT_ID, googleRegistration.getClientId());
            requestBody.put(OAuth2ParameterNames.CLIENT_SECRET, googleRegistration.getClientSecret());
           // requestBody.put(OAuth2ParameterNames.SCOPE,  String.join(" ", googleRegistration.getScopes()));
            HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);
            ResponseEntity<Map> tokenResponse = restTemplate.postForEntity(
                    googleRegistration.getProviderDetails().getTokenUri(),
                    request,
                    Map.class
            );
            String idToken = (String) Objects.requireNonNull(tokenResponse.getBody()).get("id_token");

            return ResponseEntity.ok(idToken);
        }
        else{
            return  ResponseEntity.ok("invalid code");
        }

    }

    @Operation(
            summary = "Register User REST API",
            description = "Register User REST API is used to save user into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 201 CREATED"
    )
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
