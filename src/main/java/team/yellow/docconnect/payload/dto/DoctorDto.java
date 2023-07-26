package team.yellow.docconnect.payload.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public record DoctorDto
        (
                Long id,
                @NotEmpty(message = "First name should not be empty")
                @Size(min = 2, message = "First name should be at least 2 symbols long")
                String firstName,
                @NotEmpty(message = "Last name should not be empty")
                @Size(min = 2, message = "Last name should be at least 2 symbols long")
                String lastName,
                @NotEmpty(message = "Email Should not be left empty")
                @Email(message = "Email should be a proper email")
                String email,
                @NotEmpty(message = "Summary should not be left empty")
                String summary,

                @NotNull(message = "Experience should not be null")
                @Min(value = 1, message = "Minimum for experience is 1 year")
                Integer experience,

//                @NotEmpty(message = "Education should not be left empty")
//                @Size(min = 10, message = "Education Should be at least 10 symbols long" )
                String education,

                @NotNull(message = "Average Rating should not be null")
                @Min(value = 1, message = "Minimum for Average Rating is 1")
                @Max(value = 5, message = "Maximum for Average Rating is 5")
                Double averageRating,

                @NotEmpty(message = "Image should not be left empty")
                @URL(message = "Image should be an URL")
                String imageUrl,

                @NotEmpty(message = "Address should not be left empty")
                @Size(min = 10, message = "Address should be at least 10 symbols long")
                String address,

                Long countryId,
                Long specialtyId,
                Long cityId

        )
{}
