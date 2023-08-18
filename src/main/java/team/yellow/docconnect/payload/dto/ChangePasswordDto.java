package team.yellow.docconnect.payload.dto;

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
public record ChangePasswordDto(

        @NotEmpty(message = "Please enter a password.")
        @Size(min = 8, message = "Password must be at least 8 characters long.")
        @Size(max = 100, message = "Password must not exceed 100 characters.")
        @ValidPassword
        String password,

        @NotEmpty(message = "Please enter a matching password.")
        @Size(min = 8, message = "Matching Password should be at least 8 characters long.")
        @Size(max = 100, message = "Matching Password must not exceed 100 characters.")
        @ValidPassword
        String matchingPassword
) {
}
