package team.yellow.docconnect.service.helper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import team.yellow.docconnect.entity.City;
import team.yellow.docconnect.payload.dto.CityDto;
import team.yellow.docconnect.payload.mapper.CityMapper;
import team.yellow.docconnect.payload.response.CityResponse;

import java.util.List;

@Component
public class CityServiceHelper {

    public CityResponse getCityResponse(Page<City> cities) {
        List<CityDto> allCities = CityMapper.INSTANCE.entityToDTO(cities.getContent());
        CityResponse cityResponse = new CityResponse(allCities);
        cityResponse.setPageNo(cities.getNumber());
        cityResponse.setLast(cities.isLast());
        cityResponse.setTotalPages(cities.getTotalPages());
        cityResponse.setPageSize(cities.getSize());
        cityResponse.setTotalElements(cities.getTotalElements());
        return cityResponse;
    }
}
