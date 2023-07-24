package team.yellow.docconnect.service;

import team.yellow.docconnect.payload.dto.SpecialtyDto;
import team.yellow.docconnect.payload.response.SpecialtyResponse;

public interface SpecialtyService {
    SpecialtyDto createSpecialty(SpecialtyDto specialtyDto);

    SpecialtyDto getSpecialtyById(Long id);

    SpecialtyResponse getAllSpecialties(int pageNo, int pageSize, String sortBy, String sortDir);

    SpecialtyDto updateSpecialtyById(Long id, SpecialtyDto specialtyDto);

    void deleteSpecialtyById(Long id);
}
