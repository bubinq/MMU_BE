package team.yellow.docconnect.payload.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserDto(

        Long id,

        @NotEmpty(message = "User first name should not be null or empty")
        @Size(min = 2, message = "User first name should be at least 2 characters long")
        String first_name,

        @NotEmpty(message = "User last name should not be null or empty")
        @Size(min = 2, message = "User last name should be at least 2 characters long")
        String last_name,

        @Email
        String email,

        @NotEmpty(message = "User password should not be null or empty")
        @Size(min = 8, message = "User password should be at least 8 characters long")
        String password,

        Boolean verified
) {
}
