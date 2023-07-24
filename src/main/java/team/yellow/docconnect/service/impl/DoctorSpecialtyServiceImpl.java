package team.yellow.docconnect.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team.yellow.docconnect.entity.Doctor;
import team.yellow.docconnect.entity.DoctorSpecialty;
import team.yellow.docconnect.entity.Specialty;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.payload.dto.DoctorSpecialtyDto;
import team.yellow.docconnect.payload.mapper.DoctorSpecialtyMapper;
import team.yellow.docconnect.payload.response.DoctorSpecialtyResponse;
import team.yellow.docconnect.repository.DoctorRepository;
import team.yellow.docconnect.repository.DoctorSpecialtyRepository;
import team.yellow.docconnect.repository.SpecialtyRepository;
import team.yellow.docconnect.service.DoctorSpecialtyService;

import java.util.List;

@Service
public class DoctorSpecialtyServiceImpl implements DoctorSpecialtyService {

    private final DoctorSpecialtyRepository doctorSpecialtyRepository;
    private final DoctorRepository doctorRespository;
    private final SpecialtyRepository specialtyRepository;

    public DoctorSpecialtyServiceImpl(DoctorSpecialtyRepository doctorSpecialtyRepository, DoctorRepository doctorRespository, SpecialtyRepository specialtyRepository) {
        this.doctorSpecialtyRepository = doctorSpecialtyRepository;
        this.doctorRespository = doctorRespository;
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public DoctorSpecialtyDto createDoctorSpecialty(DoctorSpecialtyDto doctorSpecialtyDto) {
        DoctorSpecialty doctorSpecialtyToCreate = DoctorSpecialtyMapper.INSTANCE.dtoToEntity(doctorSpecialtyDto);

        Doctor foundDoctor = doctorRespository.findById(doctorSpecialtyDto.doctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Id", doctorSpecialtyDto.doctorId()));
        doctorSpecialtyToCreate.setDoctor(foundDoctor);

        Specialty foundSpecialty = specialtyRepository.findById(doctorSpecialtyDto.specialtyId())
                .orElseThrow(() -> new ResourceNotFoundException("Specialty", "Id", doctorSpecialtyDto.doctorId()));
        doctorSpecialtyToCreate.setSpecialty(foundSpecialty);

        return DoctorSpecialtyMapper.INSTANCE.entityToDTO(doctorSpecialtyRepository.save(doctorSpecialtyToCreate));
    }

    @Override
    public DoctorSpecialtyDto getDoctorSpecialtyById(Long doctorSpecialtyId) {
        DoctorSpecialty foundDoctorSpecialty = doctorSpecialtyRepository.findById(doctorSpecialtyId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Specialty", "Id", doctorSpecialtyId));
        return DoctorSpecialtyMapper.INSTANCE.entityToDTO(foundDoctorSpecialty);
    }

    @Override
    public DoctorSpecialtyResponse getAllDoctorSpecialties(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<DoctorSpecialty> allDoctorSpecialties = doctorSpecialtyRepository.findAll(pageable);
        return getResponse(allDoctorSpecialties);
    }


    @Override
    public DoctorSpecialtyResponse getAllDoctorSpecialtiesByDoctorId(Long doctorId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<DoctorSpecialty> allDoctorSpecialties = doctorSpecialtyRepository.findAllByDoctor_Id(doctorId, pageable);
        return getResponse(allDoctorSpecialties);    }

    @Override
    public DoctorSpecialtyResponse getAllDoctorSpecialtiesBySpecialtyId(Long specialtyId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<DoctorSpecialty> allDoctorSpecialties = doctorSpecialtyRepository.findAllBySpecialty_Id(specialtyId, pageable);
        return getResponse(allDoctorSpecialties);    }

    @Override
    public DoctorSpecialtyDto updateDoctorSpecialtyById(DoctorSpecialtyDto doctorSpecialtyDto, Long doctorSpecialtyId) {
        DoctorSpecialty doctorSpecialtyToUpdate = doctorSpecialtyRepository.findById(doctorSpecialtyId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Specialty", "Id", doctorSpecialtyId));

        Doctor foundDoctor = doctorRespository.findById(doctorSpecialtyDto.doctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Id", doctorSpecialtyDto.doctorId()));
        doctorSpecialtyToUpdate.setDoctor(foundDoctor);

        Specialty foundSpecialty = specialtyRepository.findById(doctorSpecialtyDto.specialtyId())
                .orElseThrow(() -> new ResourceNotFoundException("Specialty", "Id", doctorSpecialtyDto.doctorId()));
        doctorSpecialtyToUpdate.setSpecialty(foundSpecialty);

        return DoctorSpecialtyMapper.INSTANCE.entityToDTO(doctorSpecialtyRepository.save(doctorSpecialtyToUpdate));    }

    @Override
    public void deleteDoctorSpecialtyById(Long doctorSpecialtyId) {
        DoctorSpecialty doctorSpecialtyToDelete = doctorSpecialtyRepository.findById(doctorSpecialtyId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Specialty", "Id", doctorSpecialtyId));
        doctorSpecialtyRepository.delete(doctorSpecialtyToDelete);
    }


    private DoctorSpecialtyResponse getResponse(Page<DoctorSpecialty> doctorSpecialties) {
        List<DoctorSpecialtyDto> allDoctorSpecialties = DoctorSpecialtyMapper.INSTANCE.entityToDTO(doctorSpecialties.getContent());
        DoctorSpecialtyResponse doctorSpecialtyResponse = new DoctorSpecialtyResponse(allDoctorSpecialties);
        doctorSpecialtyResponse.setPageNo(doctorSpecialties.getNumber());
        doctorSpecialtyResponse.setLast(doctorSpecialties.isLast());
        doctorSpecialtyResponse.setTotalPages(doctorSpecialties.getTotalPages());
        doctorSpecialtyResponse.setPageSize(doctorSpecialties.getSize());
        doctorSpecialtyResponse.setTotalElements(doctorSpecialties.getTotalElements());
        return doctorSpecialtyResponse;
    }
}
