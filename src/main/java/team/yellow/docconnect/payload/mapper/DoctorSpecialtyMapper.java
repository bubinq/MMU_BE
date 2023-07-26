//package team.yellow.docconnect.payload.mapper;
//
//import org.mapstruct.InjectionStrategy;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//import team.yellow.docconnect.entity.DoctorSpecialty;
//import team.yellow.docconnect.payload.dto.DoctorSpecialtyDto;
//import java.util.List;
//
//@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
//public interface DoctorSpecialtyMapper {
//    DoctorSpecialtyMapper INSTANCE = Mappers.getMapper(DoctorSpecialtyMapper.class);
//    @Mapping(target = "doctorId", expression = "java(doctorSpecialty.getDoctor().getId())")
//    @Mapping(target = "specialtyId", expression = "java(doctorSpecialty.getSpecialty().getId())")
//    DoctorSpecialtyDto entityToDTO(DoctorSpecialty doctorSpecialty);
//
//    List<DoctorSpecialtyDto> entityToDTO(Iterable<DoctorSpecialty> cities);
//
//    DoctorSpecialty dtoToEntity(DoctorSpecialtyDto doctorSpecialtyDto);
//
//    List<DoctorSpecialty> dtoToEntity(Iterable<DoctorSpecialtyDto> doctorSpecialtyDtos);
//
//}