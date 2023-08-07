package team.yellow.docconnect.payload.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import team.yellow.docconnect.validator.PasswordValueMatches;
import team.yellow.docconnect.validator.ValidPassword;

@PasswordValueMatches.List({
        @PasswordValueMatches(
                field = "password",
                fieldMatch = "matchingPassword",
                message = "Those passwords didn’t match. Please try again."
        )
})
public record RegisterDto(

        @NotEmpty(message = "Please enter a first name")
        @Size(min = 1, max = 49, message = "First name must be less than 50 characters long")
        String firstName,

        @NotEmpty(message = "Please enter a last name")
        @Size(min = 1, max = 49, message = "Last name must be less than 50 characters long")
        String lastName,

        @NotEmpty(message = "Please enter a password")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        @Size(max = 99, message = "Password must be less than 100 characters long")
        @ValidPassword
        String password,

        @NotEmpty(message = "Please enter a matching password")
        @Size(min = 8, message = "Matching Password should be between 8 and 20 characters")
        @Size(max = 99, message = "Matching Password must be less than 100 characters long")
        @ValidPassword
        String matchingPassword,

        @NotEmpty(message = "Please enter an email address")
        @Email(message = "Please enter a valid email address")
        String email
) {
}
