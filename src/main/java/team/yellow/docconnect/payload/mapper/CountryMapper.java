package team.yellow.docconnect.payload.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import team.yellow.docconnect.entity.Country;
import team.yellow.docconnect.payload.dto.CountryDto;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);
    CountryDto entityToDTO(Country country);

    List<CountryDto> entityToDTO(Iterable<Country> countries);

    Country dtoToEntity(CountryDto countryDto);

    List<Country> dtoToEntity(Iterable<CountryDto> countryDtos);
}
