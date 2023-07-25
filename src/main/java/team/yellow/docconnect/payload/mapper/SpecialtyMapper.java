package team.yellow.docconnect.payload.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import team.yellow.docconnect.entity.Specialty;
import team.yellow.docconnect.payload.dto.SpecialtyDto;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SpecialtyMapper {

    SpecialtyMapper INSTANCE = Mappers.getMapper(SpecialtyMapper.class);

    SpecialtyDto entityToDTO(Specialty specialty);

    List<SpecialtyDto> entityToDTO(Iterable<Specialty> specialties);

    Specialty dtoToEntity(SpecialtyDto specialtyDto);

}
