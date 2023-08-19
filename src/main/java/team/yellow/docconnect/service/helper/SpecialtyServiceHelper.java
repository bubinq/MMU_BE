package team.yellow.docconnect.service.helper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import team.yellow.docconnect.entity.Specialty;
import team.yellow.docconnect.payload.dto.SpecialtyDto;
import team.yellow.docconnect.payload.mapper.SpecialtyMapper;
import team.yellow.docconnect.payload.response.SpecialtyResponse;

import java.util.List;

@Component
public class SpecialtyServiceHelper {

    public SpecialtyResponse getSpecialtyResponse(Page<Specialty> allSpecialties) {
        List<SpecialtyDto> content = SpecialtyMapper.INSTANCE.entityToDTO(allSpecialties.getContent());
        SpecialtyResponse specialtyResponse = new SpecialtyResponse(content);
        specialtyResponse.setPageNo(allSpecialties.getNumber());
        specialtyResponse.setLast(allSpecialties.isLast());
        specialtyResponse.setTotalPages(allSpecialties.getTotalPages());
        specialtyResponse.setPageSize(allSpecialties.getSize());
        specialtyResponse.setTotalElements(allSpecialties.getTotalElements());
        return specialtyResponse;
    }
}
