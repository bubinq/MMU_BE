package team.yellow.docconnect.payload.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import team.yellow.docconnect.entity.City;
import team.yellow.docconnect.entity.Country;
import team.yellow.docconnect.payload.dto.CityDto;

import java.util.List;

@Mapper()
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    CityDto entityToDTO(City city);

    List<CityDto> entityToDTO(Iterable<City> cities);

    City dtoToEntity(CityDto cityDto);

    List<City> dtoToEntity(Iterable<CityDto> cityDtos);

    default Long mapCountryToCountryId(Country country) {
        return country != null ? country.getId() : null;
    }
}