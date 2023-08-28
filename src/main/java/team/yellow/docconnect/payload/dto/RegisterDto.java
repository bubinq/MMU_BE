package team.yellow.docconnect.payload.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import team.yellow.docconnect.validator.PasswordValueMatches;
import team.yellow.docconnect.validator.ValidPassword;


@PasswordValueMatches.List({
        @PasswordValueMatches(
                field = "password",
                fieldMatch = "matchingPassword",
                message = "Those passwords didn't match. Please try again."
        )
})
public record RegisterDto(

        @NotEmpty(message = "Please enter a first name.")
        @Length(min = 1, max = 50, message = "First name must not exceed 50 characters.")
        @Pattern(regexp = "^[A-Za-z'-. ]{1,50}$", message = "Please enter a first name containing only English letters.")
        String firstName,

        @NotEmpty(message = "Please enter a last name.")
        @Length(min = 1, max = 50, message = "Last name must not exceed 50 characters.")
        @Pattern(regexp = "^[A-Za-z'-. ]{1,50}$", message = "Please enter a last name containing only English letters.")
        String lastName,

        @NotEmpty(message = "Please enter a password.")
        @Size(min = 8, message = "Password must be at least 8 characters long.")
        @Size(max = 100, message = "Password must not exceed 100 characters.")
        @ValidPassword
        String password,

        @NotEmpty(message = "Please enter a matching password.")
        @Size(min = 8, message = "Matching Password should be at least 8 characters long.")
        @Size(max = 100, message = "Matching Password must not exceed 100 characters.")
        @ValidPassword
        String matchingPassword,

        @NotEmpty(message = "Please enter an email address.")
        @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Please enter a valid email address.")
        String email,

        @AssertTrue(message = "You must be over 18")
        Boolean isOver18
        ) {
}
