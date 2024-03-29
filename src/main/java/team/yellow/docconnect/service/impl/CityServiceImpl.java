package team.yellow.docconnect.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team.yellow.docconnect.entity.City;
import team.yellow.docconnect.entity.State;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.payload.dto.CityDto;
import team.yellow.docconnect.payload.mapper.CityMapper;
import team.yellow.docconnect.payload.response.CityResponse;
import team.yellow.docconnect.repository.CityRepository;
import team.yellow.docconnect.repository.StateRepository;
import team.yellow.docconnect.service.CityService;
import team.yellow.docconnect.service.helper.CityServiceHelper;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final StateRepository stateRepository;
    private final CityServiceHelper cityServiceHelper;

    @Override
    public CityDto createCity(CityDto cityDto, Long stateId) {
        City cityToCreate = CityMapper.INSTANCE.dtoToEntity(cityDto);
        State foundState = stateRepository.findById(stateId)
                .orElseThrow(() -> new ResourceNotFoundException("State", "Id", stateId));
        cityToCreate.setState(foundState);
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
        return cityServiceHelper.getCityResponse(allCities);
    }

    @Override
    public CityResponse getAllCitiesByStateId(Long stateId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<City> content = cityRepository.findAllByState_Id(stateId, pageable);
        return cityServiceHelper.getCityResponse(content);
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
}
