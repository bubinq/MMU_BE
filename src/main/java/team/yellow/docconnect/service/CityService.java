package team.yellow.docconnect.service;

import team.yellow.docconnect.payload.dto.CityDto;
import team.yellow.docconnect.payload.response.CityResponse;

public interface CityService {
    CityDto createCity(CityDto cityDto, Long countryId);
    CityDto getCityById(Long cityId);
    CityResponse getAllCities(int pageNo, int pageSize, String sortBy, String sortDir);
    CityResponse getAllCitiesByCountryId(Long countryId, int pageNo, int pageSize, String sortBy, String sortDir);
    CityDto updateCityById(CityDto cityDto, Long cityId);
    void deleteCityById(Long cityId);
}
