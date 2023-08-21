package team.yellow.docconnect.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team.yellow.docconnect.entity.City;
import team.yellow.docconnect.entity.State;
import team.yellow.docconnect.entity.Doctor;
import team.yellow.docconnect.entity.Specialty;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.payload.dto.DoctorDto;
import team.yellow.docconnect.payload.mapper.DoctorMapper;
import team.yellow.docconnect.payload.response.DoctorResponse;
import team.yellow.docconnect.repository.CityRepository;
import team.yellow.docconnect.repository.StateRepository;
import team.yellow.docconnect.repository.DoctorRepository;
import team.yellow.docconnect.repository.SpecialtyRepository;
import team.yellow.docconnect.service.DoctorService;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final CityRepository cityRepository;
    private final StateRepository stateRepository;
    private final SpecialtyRepository specialtyRepository;


    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto, Long cityId, Long stateId, Long specialtyId) {
        City foundCity = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City", "Id", cityId));
        State foundState = stateRepository.findById(stateId)
                .orElseThrow(() -> new ResourceNotFoundException("State", "Id", stateId));
        Specialty foundSpecialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty", "id", specialtyId));
        Doctor doctor = DoctorMapper.INSTANCE.dtoToEntity(doctorDto);
        doctor.setCity(foundCity);
        doctor.setState(foundState);
        doctor.setSpecialty(foundSpecialty);
        return DoctorMapper.INSTANCE.entityToDTO(doctorRepository.save(doctor));
    }

    @Override
    public DoctorDto getDoctorById(Long doctorId) {
        Doctor foundDoctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor","Id", doctorId));
        return  DoctorMapper.INSTANCE.entityToDTO(foundDoctor);
    }

    @Override
    public DoctorResponse getAllDoctorsByCityId(Long cityId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        City foundCity = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City", "Id", cityId));
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Doctor> content = doctorRepository.findAllByCity_Id(foundCity.getId(), pageable);
        return getResponse(content);
    }

    @Override
    public DoctorDto updateDoctorById(Long doctorId, DoctorDto doctorDto) {
        Doctor foundDoctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor","Id", doctorId));
        City foundCity = cityRepository.findById(doctorDto.cityId())
                .orElseThrow(() -> new ResourceNotFoundException("City", "Id", doctorDto.cityId()));
        State foundState = stateRepository.findById(doctorDto.stateId())
                .orElseThrow(() -> new ResourceNotFoundException("State", "Id", doctorDto.stateId()));
        foundDoctor.setEducation(doctorDto.education());
        foundDoctor.setExperience(doctorDto.experience());
        foundDoctor.setSummary(doctorDto.summary());
        foundDoctor.setAverageRating(doctorDto.averageRating());
        foundDoctor.setFirstName(doctorDto.firstName());
        foundDoctor.setLastName(doctorDto.lastName());
        foundDoctor.setEmail(doctorDto.email());
        foundDoctor.setCity(foundCity);
        foundDoctor.setState(foundState);
        return DoctorMapper.INSTANCE.entityToDTO(doctorRepository.save(foundDoctor));
    }

    @Override
    public void deleteDoctorById(Long doctorId) {
        Doctor foundDoctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor","Id", doctorId));
        doctorRepository.delete(foundDoctor);
    }

    private DoctorResponse getResponse(Page<Doctor> doctors) {
        List<DoctorDto> content = DoctorMapper.INSTANCE.entityToDTO(doctors.getContent());
        DoctorResponse doctorResponse = new DoctorResponse(content);
        doctorResponse.setPageNo(doctors.getNumber());
        doctorResponse.setLast(doctors.isLast());
        doctorResponse.setTotalPages(doctors.getTotalPages());
        doctorResponse.setPageSize(doctors.getSize());
        doctorResponse.setTotalElements(doctors.getTotalElements());
        return doctorResponse;
    }

    @Override
    public DoctorResponse getSearchedDoctors(int pageNo, int pageSize, String sortBy, String sortDir, Long specialtyId, Long cityId, String name) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        String searchableName = name != null ? "%" + name.trim().toLowerCase().replace(" ", "%") + "%" : null;

        Page<Doctor> doctors = doctorRepository.findBySearchParams(specialtyId, cityId, searchableName, pageable);
        return getResponse(doctors);
    }
}
