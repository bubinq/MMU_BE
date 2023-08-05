package team.yellow.docconnect.service;

import team.yellow.docconnect.payload.dto.LoginDto;
import team.yellow.docconnect.payload.dto.RegisterDto;

public interface AuthenticationService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
