package team.yellow.docconnect.service;

import team.yellow.docconnect.payload.dto.dto.CityDto;
import team.yellow.docconnect.payload.dto.response.CityResponse;

public interface CityService {
    CityDto createCity(CityDto cityDto);
    CityDto getCityById(Long cityId);
    CityResponse getAllCities(int pageNo, int pageSize, String sortBy, String sortDir);
    CityDto updateCityById(CityDto cityDto, Long cityId);
    void deleteCityById(Long cityId);
}
