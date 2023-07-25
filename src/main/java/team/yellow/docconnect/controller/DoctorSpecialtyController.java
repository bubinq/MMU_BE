package team.yellow.docconnect.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.yellow.docconnect.payload.dto.DoctorSpecialtyDto;
import team.yellow.docconnect.payload.response.DoctorSpecialtyResponse;
import team.yellow.docconnect.service.DoctorSpecialtyService;
import team.yellow.docconnect.utils.AppConstants;

@RestController
@RequestMapping("api/v1/doctor-specialties")
public class DoctorSpecialtyController {

    private final DoctorSpecialtyService doctorSpecialtyService;

    public DoctorSpecialtyController(DoctorSpecialtyService doctorSpecialtyService) {
        this.doctorSpecialtyService = doctorSpecialtyService;
    }

    @PostMapping("/doctors/{doctorId}/specialties/{specialityId}")
    public ResponseEntity<DoctorSpecialtyDto> createDoctorSpecialty(@RequestBody @Valid DoctorSpecialtyDto doctorSpecialtyDto,
                                                                    @PathVariable Long doctorId,
                                                                    @PathVariable Long specialityId
    ){
       return new ResponseEntity<>(doctorSpecialtyService.createDoctorSpecialty(doctorSpecialtyDto, doctorId, specialityId), HttpStatus.CREATED);
    }

    @GetMapping("/{doctorSpecialtyId}")
    public ResponseEntity<DoctorSpecialtyDto> getDoctorSpecialtyById(@PathVariable Long doctorSpecialtyId){
        return ResponseEntity.ok(doctorSpecialtyService.getDoctorSpecialtyById(doctorSpecialtyId));
    }

    @GetMapping("")
    public ResponseEntity<DoctorSpecialtyResponse> getAllDoctorsSpecialties
            (
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ){
        return ResponseEntity.ok(doctorSpecialtyService.getAllDoctorSpecialties(pageNo, pageSize, sortBy, sortDir));
    }

    @GetMapping("doctors/{doctorId}")
    public ResponseEntity<DoctorSpecialtyResponse> getAllDoctorsSpecialtiesByDoctorId
            (
                    @PathVariable Long doctorId,
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ){
        return ResponseEntity.ok(doctorSpecialtyService.getAllDoctorSpecialtiesByDoctorId(doctorId, pageNo, pageSize, sortBy, sortDir));
    }
    @GetMapping("specialties/{specialtyId}")
    public ResponseEntity<DoctorSpecialtyResponse> getAllDoctorsSpecialtiesBySpecialtyId
            (
                    @PathVariable Long specialtyId,
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ){
        return ResponseEntity.ok(doctorSpecialtyService.getAllDoctorSpecialtiesBySpecialtyId(specialtyId, pageNo, pageSize, sortBy, sortDir));
    }

    @PutMapping("/{doctorSpecialtyId}")
    public ResponseEntity<DoctorSpecialtyDto> updateDoctorSpecialtyById(@PathVariable Long doctorSpecialtyId, @RequestBody @Valid DoctorSpecialtyDto doctorSpecialtyDto){
        return ResponseEntity.ok(doctorSpecialtyService.updateDoctorSpecialtyById(doctorSpecialtyDto, doctorSpecialtyId));
    }

    @DeleteMapping("/{doctorSpecialtyId}")
    public ResponseEntity<String> deleteDoctorSpecialtyById(@PathVariable Long doctorSpecialtyId){
        doctorSpecialtyService.deleteDoctorSpecialtyById(doctorSpecialtyId);
        return ResponseEntity.ok(AppConstants.SUCCESSFULLY_DELETED_MESSAGE);
    }

}
