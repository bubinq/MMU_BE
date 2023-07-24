package team.yellow.docconnect.payload.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record SpecialtyDto (
        Long specialty_id,

        @NotEmpty(message = "Specialty name should not be null or empty")
        @Size(min = 2, message = "Specialty name should be at least 2 characters long")
        String name,

        @NotEmpty(message = "Image URL should not be null or empty")
        String image_url
){

}
