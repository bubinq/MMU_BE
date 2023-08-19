package team.yellow.docconnect.service;

import team.yellow.docconnect.payload.dto.HospitalDto;
import team.yellow.docconnect.payload.response.HospitalResponse;

public interface HospitalService {

    HospitalDto createHospital(HospitalDto hospitalDto, Long cityId, Long stateId);

    HospitalDto getHospitalById(Long id);

    HospitalResponse getAllHospitalsByCityId(Long cityId, int pageNo, int pageSize, String sortBy, String sortDir);

    HospitalResponse getAllHospitalsByStateId(Long stateId, int pageNo, int pageSize, String sortBy, String sortDir);

    HospitalDto updateHospitalById(Long id, HospitalDto hospitalDto);

    void deleteHospitalById(Long id);
}