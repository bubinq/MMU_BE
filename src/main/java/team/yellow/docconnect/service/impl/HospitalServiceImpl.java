package team.yellow.docconnect.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team.yellow.docconnect.entity.City;
import team.yellow.docconnect.entity.Hospital;
import team.yellow.docconnect.entity.State;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.payload.dto.HospitalDto;
import team.yellow.docconnect.payload.mapper.HospitalMapper;
import team.yellow.docconnect.payload.response.HospitalResponse;
import team.yellow.docconnect.repository.CityRepository;
import team.yellow.docconnect.repository.HospitalRepository;
import team.yellow.docconnect.repository.StateRepository;
import team.yellow.docconnect.service.HospitalService;
import team.yellow.docconnect.service.helper.HospitalServiceHelper;

@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    private final CityRepository cityRepository;
    private final HospitalServiceHelper hospitalServiceHelper;
    private final StateRepository stateRepository;

    public HospitalServiceImpl(HospitalRepository hospitalRepository, CityRepository cityRepository, HospitalServiceHelper hospitalServiceHelper, StateRepository stateRepository) {
        this.hospitalRepository = hospitalRepository;
        this.cityRepository = cityRepository;
        this.hospitalServiceHelper = hospitalServiceHelper;
        this.stateRepository = stateRepository;
    }

    @Override
    public HospitalDto createHospital(HospitalDto hospitalDto, Long stateId, Long cityId) {
        City foundCity = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City", "Id", cityId));
        State foundState = stateRepository.findById(stateId)
                .orElseThrow(() -> new ResourceNotFoundException("State", "Id", stateId));
        Hospital hospital = HospitalMapper.INSTANCE.dtoToEntity(hospitalDto);
        hospital.setCity(foundCity);
        hospital.setState(foundState);
        return HospitalMapper.INSTANCE.entityToDTO(hospitalRepository.save(hospital));
    }

    @Override
    public HospitalDto getHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "id", id));
        return HospitalMapper.INSTANCE.entityToDTO(hospital);
    }

    @Override
    public HospitalResponse getAllHospitalsByCityId(Long cityId, int pageNo, int pageSize, String sortBy, String sortDir) {
        City foundCity = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City", "Id", cityId));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Hospital> allHospitals = hospitalRepository.findAllByCity_Id(foundCity.getId(), pageable);
        return hospitalServiceHelper.getHospitalResponse(allHospitals);
    }

    @Override
    public HospitalResponse getAllHospitalsByStateId(Long stateId, int pageNo, int pageSize, String sortBy, String sortDir) {
        State foundState = stateRepository.findById(stateId)
                .orElseThrow(() -> new ResourceNotFoundException("State", "Id", stateId));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Hospital> allHospitals = hospitalRepository.findAllByState_Id(foundState.getId(), pageable);
        return hospitalServiceHelper.getHospitalResponse(allHospitals);
    }

    @Override
    public HospitalDto updateHospitalById(Long id, HospitalDto hospitalDto) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "id", id));
        City foundCity = cityRepository.findById(hospitalDto.cityId())
                .orElseThrow(() -> new ResourceNotFoundException("City", "Id", hospitalDto.cityId()));
        State foundState = stateRepository.findById(hospitalDto.stateId())
                .orElseThrow(() -> new ResourceNotFoundException("State", "Id", hospitalDto.stateId()));
        hospital.setCity(foundCity);
        hospital.setState(foundState);
        hospital.setAddress(hospitalDto.address());
        hospital.setName(hospitalDto.name());
        return HospitalMapper.INSTANCE.entityToDTO(hospitalRepository.save(hospital));
    }

    @Override
    public void deleteHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "id", id));
        hospitalRepository.delete(hospital);
    }
}