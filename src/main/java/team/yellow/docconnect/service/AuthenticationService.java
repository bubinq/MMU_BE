package team.yellow.docconnect.service;

import team.yellow.docconnect.payload.dto.LoginDto;
import team.yellow.docconnect.payload.dto.RegisterDto;

import java.io.IOException;

public interface AuthenticationService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

    String googleSignIn(String idToken) throws IOException;
}
