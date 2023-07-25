package team.yellow.docconnect.payload.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import team.yellow.docconnect.entity.Hospital;
import team.yellow.docconnect.payload.dto.HospitalDto;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface HospitalMapper {

    HospitalMapper INSTANCE = Mappers.getMapper(HospitalMapper.class);

    HospitalDto entityToDTO(Hospital hospital);

    List<HospitalDto> entityToDTO(Iterable<Hospital> hospitals);

    Hospital dtoToEntity(HospitalDto hospitalDto);
}
