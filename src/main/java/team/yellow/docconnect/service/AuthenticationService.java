package team.yellow.docconnect.service;

import team.yellow.docconnect.payload.dto.ChangePasswordDto;
import team.yellow.docconnect.payload.dto.ForgotPasswordDto;
import team.yellow.docconnect.payload.dto.LoginDto;
import team.yellow.docconnect.payload.dto.RegisterDto;

import java.io.IOException;

public interface AuthenticationService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

    String googleSignIn(String idToken) throws IOException;

    String verifyEmail( String token);

    String changePassword(ChangePasswordDto changePasswordDto, String token);

    void forgotPassword(ForgotPasswordDto forgotPasswordDto);

    String resendForgotPassword(String token);

    String sendEmailVerification(String email);
}
