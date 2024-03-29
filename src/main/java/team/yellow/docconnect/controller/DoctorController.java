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
import team.yellow.docconnect.payload.dto.DoctorDto;
import team.yellow.docconnect.payload.response.DoctorResponse;
import team.yellow.docconnect.service.DoctorService;
import team.yellow.docconnect.utils.AppConstants;
import team.yellow.docconnect.utils.Messages;

@RestController
@RequestMapping("api/v1")
@Tag(name = "CRUD REST APIs for Doctor Resource")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Operation(
            summary = "Create Doctor REST API",
            description = "Create Doctor REST API is used to save doctor into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )@SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("states/{stateId}/cities/{cityId}/specialties/{specialtyId}/doctors")
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody @Valid DoctorDto doctorDto, @PathVariable Long cityId,
                                                  @PathVariable Long stateId, @PathVariable Long specialtyId){
       return new ResponseEntity<>(doctorService.createDoctor(doctorDto, cityId, stateId, specialtyId), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Doctor By Id REST API",
            description = "Get Doctor By Id REST API is used to get a single doctor from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )

    @GetMapping("doctors/{doctorId}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long doctorId){
        return ResponseEntity.ok(doctorService.getDoctorById(doctorId));
    }


    @Operation(
            summary = "Get All Doctors By City Id REST API",
            description = "Get All Doctors By City Id REST API is used to fetch all the doctors from the database by city id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
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

    @Operation(
            summary = "Update Doctor REST API",
            description = "Update Doctor REST API is used to update a particular doctor in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("doctors/{doctorId}")
    public ResponseEntity<DoctorDto> updateDoctorById(@PathVariable Long doctorId, @RequestBody @Valid DoctorDto doctorDto){
        return ResponseEntity.ok(doctorService.updateDoctorById(doctorId, doctorDto));
    }

    @Operation(
            summary = "Delete Doctor REST API",
            description = "Delete Doctor REST API is used to delete doctor from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("doctors/{doctorId}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable Long doctorId){
        doctorService.deleteDoctorById(doctorId);
        return ResponseEntity.ok(Messages.SUCCESSFULLY_DELETED_DOCTOR + doctorId);
    }

    @Operation(
            summary = "Get All Doctors Searched REST API",
            description = "Get All Doctors Searched REST API is used to fetch all the doctors according to the search params from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("doctors")
    public ResponseEntity<DoctorResponse> getSearchedDoctors(
                                                  @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                                  @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                                  @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                                  @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
                                                  @RequestParam(value = "specialtyId", required = false) Long specialtyId,
                                                  @RequestParam(value = "cityId", required = false) Long cityId,
                                                  @RequestParam(value = "name", required = false) String name
    ){
        return ResponseEntity.ok(doctorService.getSearchedDoctors(pageNo, pageSize, sortBy, sortDir,specialtyId,cityId,name));
    }

}
