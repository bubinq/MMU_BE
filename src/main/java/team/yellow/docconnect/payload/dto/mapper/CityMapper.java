package team.yellow.docconnect.payload.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import team.yellow.docconnect.entity.City;
import team.yellow.docconnect.payload.dto.dto.CityDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
    @Mapping(target = "name", source = "name")
    CityDto entityToDTO(City city);

    List<CityDto> entityToDTO(Iterable<City> cities);

    @Mapping(target = "name", source = "name")
    City dtoToEntity(CityDto cityDto);

    List<City> dtoToEntity(Iterable<CityDto> cityDtos);
}