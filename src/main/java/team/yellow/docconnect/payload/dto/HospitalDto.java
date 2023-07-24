package team.yellow.docconnect.payload.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record HospitalDto(

        Long id,

        @NotEmpty(message = "Hospital name should not be null or empty")
        @Size(min = 4, message = "Hospital name should be at least 4 characters long")
        String name,

        @NotEmpty(message = "Hospital address should not be null or empty")
        @Size(min = 4, message = "Hospital address should be at least 4 characters long")
        String address,

        Long city_id
) {
}