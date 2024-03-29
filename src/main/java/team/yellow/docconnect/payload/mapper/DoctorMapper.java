package team.yellow.docconnect.payload.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import team.yellow.docconnect.entity.Doctor;
import team.yellow.docconnect.payload.dto.DoctorDto;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);
    @Mapping(target = "cityId", expression = "java(doctor.getCity().getId())")
    @Mapping(target = "stateId", expression = "java(doctor.getState().getId())")
    @Mapping(target = "specialtyId", expression = "java(doctor.getSpecialty().getId())")
    @Mapping(expression = "java( String.valueOf(doctor.getSpecialty().getName()) )", target = "specialtyName")
    DoctorDto entityToDTO(Doctor doctor);

    List<DoctorDto> entityToDTO(Iterable<Doctor> doctors);

    Doctor dtoToEntity(DoctorDto doctorDto);

    List<Doctor> dtoToEntity(Iterable<DoctorDto> doctorDtos);



}
