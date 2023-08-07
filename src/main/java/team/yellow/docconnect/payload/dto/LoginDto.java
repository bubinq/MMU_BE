package team.yellow.docconnect.payload.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record LoginDto(

        @NotEmpty(message = "Please enter your email address")
        @Email(message = "Please enter a valid email address")
        String email,

        @NotEmpty(message = "Please enter your password")
        String password
) {
}
