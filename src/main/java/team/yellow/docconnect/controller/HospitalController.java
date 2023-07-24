package team.yellow.docconnect.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.yellow.docconnect.payload.dto.HospitalDto;
import team.yellow.docconnect.payload.response.HospitalResponse;
import team.yellow.docconnect.service.HospitalService;
import team.yellow.docconnect.utils.AppConstants;

@RestController
@RequestMapping("api/v1/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping
    public ResponseEntity<HospitalDto> createHospital(@Valid @RequestBody HospitalDto hospitalDto) {
        return new ResponseEntity<>(hospitalService.createHospital(hospitalDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<HospitalDto> getHospitalById(@PathVariable Long id) {
        return ResponseEntity.ok(hospitalService.getHospitalById(id));
    }

    @GetMapping
    public ResponseEntity<HospitalResponse> getAllHospitals
            (
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ) {
        return ResponseEntity.ok(hospitalService.getAllHospitals(pageNo, pageSize, sortBy, sortDir));
    }

    @PutMapping("{id}")
    public ResponseEntity<HospitalDto> updateHospitalById(@RequestBody @Valid HospitalDto hospitalDto, @PathVariable Long id) {
        return ResponseEntity.ok(hospitalService.updateHospitalById(id, hospitalDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteHospitalById(@PathVariable Long id) {
        hospitalService.deleteHospitalById(id);
        return ResponseEntity.ok(AppConstants.SUCCESSFULLY_DELETED_MESSAGE);
    }
}
