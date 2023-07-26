package team.yellow.docconnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.yellow.docconnect.payload.dto.DoctorDto;
import team.yellow.docconnect.payload.response.DoctorResponse;
import team.yellow.docconnect.service.DoctorService;
import team.yellow.docconnect.utils.AppConstants;
@CrossOrigin(maxAge = 999999999)
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
    )
    @PostMapping("cities/{cityId}/doctors")
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody @Valid DoctorDto doctorDto, @PathVariable Long cityId){
       return new ResponseEntity<>(doctorService.createDoctor(doctorDto, cityId), HttpStatus.CREATED);
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
            summary = "Get All Doctors REST API",
            description = "Get All Doctors REST API is used to fetch all the doctors from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
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
    @DeleteMapping("doctors/{doctorId}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable Long doctorId){
        doctorService.deleteDoctorById(doctorId);
        return ResponseEntity.ok("Successfully deleted Doctor with id:" + doctorId);
    }

}
