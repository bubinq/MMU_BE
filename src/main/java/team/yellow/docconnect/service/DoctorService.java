package team.yellow.docconnect.service;

import team.yellow.docconnect.payload.dto.DoctorDto;
import team.yellow.docconnect.payload.response.DoctorResponse;

public interface DoctorService {

    DoctorDto createDoctor(DoctorDto doctorDto, Long cityId, Long countryId, Long specialtyId);
    DoctorDto getDoctorById(Long doctorId);

    DoctorResponse getAllDoctorsByCityId(Long cityId, int pageNo, int pageSize, String sortBy, String sortDir);

    DoctorDto updateDoctorById(Long doctorId, DoctorDto doctorDto);

    void deleteDoctorById(Long doctorId);

    DoctorResponse getSearchedDoctors(int pageNo, int pageSize, String sortBy, String sortDir, Long specialtyId, Long cityId, String name);

    }
