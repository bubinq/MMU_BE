package team.yellow.docconnect.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.yellow.docconnect.payload.dto.DoctorDto;
import team.yellow.docconnect.payload.response.DoctorResponse;
import team.yellow.docconnect.service.DoctorService;
import team.yellow.docconnect.utils.AppConstants;

@RestController
@RequestMapping("api/v1")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("cities/{cityId}/doctors")
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody @Valid DoctorDto doctorDto, @PathVariable Long cityId){
       return new ResponseEntity<>(doctorService.createDoctor(doctorDto, cityId), HttpStatus.CREATED);
    }

    @GetMapping("doctors/{doctorId}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long doctorId){
        return ResponseEntity.ok(doctorService.getDoctorById(doctorId));
    }

    @GetMapping("doctors")
    public ResponseEntity<DoctorResponse> getAllDoctors
            (
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ){
        return ResponseEntity.ok(doctorService.getAllDoctors(pageNo, pageSize, sortBy, sortDir));
    }

    @GetMapping("cities/{cityId}/doctors")
    public ResponseEntity<DoctorResponse> getAllDoctorsByCity
            (
                    @PathVariable Long cityId,
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ){
        return ResponseEntity.ok(doctorService.getAllDoctorsByCityId(cityId, pageNo, pageSize, sortBy, sortDir));
    }

    @PutMapping("doctors/{doctorId}")
    public ResponseEntity<DoctorDto> updateDoctorById(@PathVariable Long doctorId, @RequestBody @Valid DoctorDto doctorDto){
        return ResponseEntity.ok(doctorService.updateDoctorById(doctorId, doctorDto));
    }

    @DeleteMapping("doctors/{doctorId}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable Long doctorId){
        doctorService.deleteDoctorById(doctorId);
        return ResponseEntity.ok("Successfully deleted Doctor with id:" + doctorId);
    }

}
