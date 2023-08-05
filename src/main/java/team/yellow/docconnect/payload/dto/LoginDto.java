package team.yellow.docconnect.payload.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginDto(

        @NotEmpty(message = "Email should not be null or empty")
        String email,

        @NotEmpty(message = "Password should not be null or empty")
        String password
) {
}
