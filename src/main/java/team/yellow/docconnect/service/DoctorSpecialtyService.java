package team.yellow.docconnect.service;

import team.yellow.docconnect.payload.dto.DoctorSpecialtyDto;
import team.yellow.docconnect.payload.response.DoctorSpecialtyResponse;

public interface DoctorSpecialtyService {
    DoctorSpecialtyDto createDoctorSpecialty(DoctorSpecialtyDto doctorSpecialtyDto);
    DoctorSpecialtyDto getDoctorSpecialtyById(Long doctorSpecialtyId);
    DoctorSpecialtyResponse getAllDoctorSpecialties(int pageNo, int pageSize, String sortBy, String sortDir);
    DoctorSpecialtyDto updateDoctorSpecialtyById(DoctorSpecialtyDto doctorSpecialtyDto, Long doctorSpecialtyId);
    void deleteDoctorSpecialtyById(Long doctorSpecialtyId);
    DoctorSpecialtyResponse getAllDoctorSpecialtiesByDoctorId(Long doctorId, int pageNo, int pageSize, String sortBy, String sortDir);
    DoctorSpecialtyResponse getAllDoctorSpecialtiesBySpecialtyId(Long doctorId, int pageNo, int pageSize, String sortBy, String sortDir);
}
