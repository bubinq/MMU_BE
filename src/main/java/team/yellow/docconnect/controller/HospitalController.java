package team.yellow.docconnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team.yellow.docconnect.payload.dto.HospitalDto;
import team.yellow.docconnect.payload.response.HospitalResponse;
import team.yellow.docconnect.service.HospitalService;
import team.yellow.docconnect.utils.AppConstants;
import team.yellow.docconnect.utils.Messages;

@CrossOrigin(maxAge = 999999999)
@RestController
@RequestMapping("api/v1")
@Tag(name = "CRUD REST APIs for Hospital Resource")
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @Operation(
            summary = "Create Hospital REST API",
            description = "Create Hospital REST API is used to save hospital into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("states/{stateId}/cities/{cityId}/hospitals")
    public ResponseEntity<HospitalDto> createHospital(@Valid @RequestBody HospitalDto hospitalDto,
                                                      @PathVariable Long stateId, @PathVariable Long cityId) {
        return new ResponseEntity<>(hospitalService.createHospital(hospitalDto, stateId, cityId), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Hospital By Id REST API",
            description = "Get Hospital By Id REST API is used to get a single hospital from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("hospitals/{id}")
    public ResponseEntity<HospitalDto> getHospitalById(@PathVariable Long id) {
        return ResponseEntity.ok(hospitalService.getHospitalById(id));
    }

    @Operation(
            summary = "Get All Hospitals By City REST API",
            description = "Get All Hospitals By City REST API is used to fetch all the hospitals by city id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("cities/{cityId}/hospitals")
    public ResponseEntity<HospitalResponse> getAllHospitalsByCity
            (
                    @PathVariable Long cityId,
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ) {
        return ResponseEntity.ok(hospitalService.getAllHospitalsByCityId(cityId, pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Get All Hospitals By State REST API",
            description = "Get All Hospitals By State REST API is used to fetch all the hospitals by state id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("states/{stateId}/hospitals")
    public ResponseEntity<HospitalResponse> getAllHospitalsByState
            (
                    @PathVariable Long stateId,
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ) {
        return ResponseEntity.ok(hospitalService.getAllHospitalsByStateId(stateId, pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Update Hospital REST API",
            description = "Update Hospital REST API is used to update a particular hospital in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("hospitals/{id}")
    public ResponseEntity<HospitalDto> updateHospitalById(@RequestBody @Valid HospitalDto hospitalDto, @PathVariable Long id) {
        return ResponseEntity.ok(hospitalService.updateHospitalById(id, hospitalDto));
    }

    @Operation(
            summary = "Delete Hospital REST API",
            description = "Delete Hospital REST API is used to delete hospital from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("hospitals/{id}")
    public ResponseEntity<String> deleteHospitalById(@PathVariable Long id) {
        hospitalService.deleteHospitalById(id);
        return ResponseEntity.ok(Messages.SUCCESSFULLY_DELETED_MESSAGE);
    }
}
