package team.yellow.docconnect.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team.yellow.docconnect.entity.Hospital;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.payload.dto.HospitalDto;
import team.yellow.docconnect.payload.mapper.HospitalMapper;
import team.yellow.docconnect.payload.response.HospitalResponse;
import team.yellow.docconnect.repository.HospitalRepository;
import team.yellow.docconnect.service.HospitalService;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public HospitalDto createHospital(HospitalDto hospitalDto) {
        Hospital hospital = HospitalMapper.INSTANCE.dtoToEntity(hospitalDto);
        return HospitalMapper.INSTANCE.entityToDTO(hospitalRepository.save(hospital));
    }

    @Override
    public HospitalDto getHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "id", id));
        return HospitalMapper.INSTANCE.entityToDTO(hospital);
    }

    @Override
    public HospitalResponse getAllHospitals(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Hospital> allHospitals = hospitalRepository.findAll(pageable);
        return getHospitalResponse(allHospitals);
    }

    @Override
    public HospitalDto updateHospitalById(Long id, HospitalDto hospitalDto) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "id", id));
        hospital.setCity(hospitalDto.city());
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

    private HospitalResponse getHospitalResponse(Page<Hospital> allHospitals) {
        List<HospitalDto> content = HospitalMapper.INSTANCE.entityToDTO(allHospitals.getContent());
        return new HospitalResponse(
                content,
                allHospitals.getNumber(),
                allHospitals.getSize(),
                allHospitals.getTotalElements(),
                allHospitals.getTotalPages(),
                allHospitals.isLast());
    }
}