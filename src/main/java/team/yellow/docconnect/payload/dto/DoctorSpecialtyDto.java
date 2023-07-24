package team.yellow.docconnect.payload.dto;

import jakarta.validation.constraints.NotNull;

public record DoctorSpecialtyDto(
        Long id,
        @NotNull(message = "Doctor Id cannot be left null")
        Long doctorId,
        @NotNull(message = "Specialty Id cannot be left null")
        Long specialtyId
)
{ }
