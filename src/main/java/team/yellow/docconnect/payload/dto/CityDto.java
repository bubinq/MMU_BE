package team.yellow.docconnect.payload.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CityDto(
        Long id,
        @NotNull(message = "Name Cannot be left null")
        @Size(min = 2, message = "City name Should be at least 2 symbols long")
        @NotBlank(message = "City name should not be left blank")
        String name,
        String stateName
)
{ }
