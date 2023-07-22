package team.yellow.docconnect.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team.yellow.docconnect.entity.City;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.payload.dto.CityDto;
import team.yellow.docconnect.payload.mapper.CityMapper;
import team.yellow.docconnect.payload.response.CityResponse;
import team.yellow.docconnect.repository.CityRepository;
import team.yellow.docconnect.service.CityService;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public CityDto createCity(CityDto cityDto) {
        City cityToCreate = CityMapper.INSTANCE.dtoToEntity(cityDto);
        return CityMapper.INSTANCE.entityToDTO(cityRepository.save(cityToCreate));
    }

    @Override
    public CityDto getCityById(Long cityId) {
        City foundCity = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City", "Id", cityId));
        return CityMapper.INSTANCE.entityToDTO(foundCity);
    }

    @Override
    public CityResponse getAllCities(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<City> allCities = cityRepository.findAll(pageable);
        return getResponse(allCities);
    }


    @Override
    public CityDto updateCityById(CityDto cityDto, Long cityId) {
        City foundCity = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City", "Id", cityId));
        foundCity.setName(cityDto.name());
        return CityMapper.INSTANCE.entityToDTO(cityRepository.save(foundCity));
    }

    @Override
    public void deleteCityById(Long cityId) {
        City foundCity = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City", "Id", cityId));
        cityRepository.delete(foundCity);
    }

    private CityResponse getResponse(Page<City> cities) {
        List<CityDto> allCities = CityMapper.INSTANCE.entityToDTO(cities.getContent());
        return new CityResponse
                (
                        allCities,
                        cities.getNumber(),
                        cities.getSize(),
                        cities.getTotalElements(),
                        cities.getTotalPages(),
                        cities.isLast()
                );
    }
}
