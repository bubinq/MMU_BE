package team.yellow.docconnect.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team.yellow.docconnect.entity.Specialty;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.payload.dto.SpecialtyDto;
import team.yellow.docconnect.payload.mapper.SpecialtyMapper;
import team.yellow.docconnect.payload.response.SpecialtyResponse;
import team.yellow.docconnect.repository.SpecialtyRepository;
import team.yellow.docconnect.service.SpecialtyService;
import team.yellow.docconnect.service.helper.SpecialtyServiceHelper;

@Service
@AllArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyServiceHelper specialtyServiceHelper;

    @Override
    public SpecialtyDto createSpecialty(SpecialtyDto specialtyDto) {
        Specialty specialty = SpecialtyMapper.INSTANCE.dtoToEntity(specialtyDto);
        return SpecialtyMapper.INSTANCE.entityToDTO(specialtyRepository.save(specialty));
    }

    @Override
    public SpecialtyDto getSpecialtyById(Long id) {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty", "id", id));
        return SpecialtyMapper.INSTANCE.entityToDTO(specialty);
    }

    @Override
    public SpecialtyResponse getAllSpecialties(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Specialty> allSpecialties = specialtyRepository.findAll(pageable);
        return specialtyServiceHelper.getSpecialtyResponse(allSpecialties);
    }

    @Override
    public SpecialtyDto updateSpecialtyById(Long id, SpecialtyDto specialtyDto) {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty", "id", id));
        specialty.setName(specialtyDto.name());
        specialty.setImage_url(specialtyDto.image_url());
        return SpecialtyMapper.INSTANCE.entityToDTO(specialtyRepository.save(specialty));    }

    @Override
    public void deleteSpecialtyById(Long id) {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty", "id", id));
        specialtyRepository.delete(specialty);
    }
}
