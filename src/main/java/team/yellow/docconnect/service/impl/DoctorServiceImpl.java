package team.yellow.docconnect.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team.yellow.docconnect.entity.City;
import team.yellow.docconnect.entity.Doctor;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.payload.dto.DoctorDto;
import team.yellow.docconnect.payload.mapper.DoctorMapper;
import team.yellow.docconnect.payload.response.DoctorResponse;
import team.yellow.docconnect.repository.CityRepository;
import team.yellow.docconnect.repository.DoctorRepository;
import team.yellow.docconnect.service.DoctorService;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final CityRepository cityRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository, CityRepository cityRepository) {
        this.doctorRepository = doctorRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto, Long cityId) {
        City foundCity = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City", "Id", cityId));
        Doctor doctor = DoctorMapper.INSTANCE.dtoToEntity(doctorDto);
        doctor.setCity(foundCity);
        return DoctorMapper.INSTANCE.entityToDTO(doctorRepository.save(doctor));
    }

    @Override
    public DoctorDto getDoctorById(Long doctorId) {
        Doctor foundDoctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor","Id", doctorId));
        return  DoctorMapper.INSTANCE.entityToDTO(foundDoctor);
    }

    @Override
    public DoctorResponse getAllDoctors(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Doctor> doctors = doctorRepository.findAll(pageable);
        return getResponse(doctors);
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
        foundDoctor.setEducation(doctorDto.education());
        foundDoctor.setExperience(doctorDto.experience());
        foundDoctor.setSummary(doctorDto.summary());
        foundDoctor.setAverageRating(doctorDto.averageRating());
        foundDoctor.setFirstName(doctorDto.firstName());
        foundDoctor.setLastName(doctorDto.lastName());
        foundDoctor.setEmail(doctorDto.email());
        foundDoctor.setCity(foundCity);
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
}
