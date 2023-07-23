package team.yellow.docconnect.service;

import team.yellow.docconnect.payload.dto.CountryDto;
import team.yellow.docconnect.payload.response.CountryResponse;

public interface CountryService {
    CountryDto createCountry(CountryDto countryDto);
    CountryDto getCountryById(Long countryId);
    CountryResponse getAllCountries(int pageNo, int pageSize, String sortBy, String sortDir);
    CountryDto updateCountryById(CountryDto countryDto, Long countryId);
    void deleteCountryId(Long countryId);
}
