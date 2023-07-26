//package team.yellow.docconnect.controller;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import team.yellow.docconnect.payload.dto.DoctorSpecialtyDto;
//import team.yellow.docconnect.payload.response.DoctorSpecialtyResponse;
//import team.yellow.docconnect.service.DoctorSpecialtyService;
//import team.yellow.docconnect.utils.AppConstants;
//
//@RestController
//@RequestMapping("api/v1/doctor-specialties")
//@Tag(name = "CRUD REST APIs for Doctor Specialty Resource")
//public class DoctorSpecialtyController {
//
//    private final DoctorSpecialtyService doctorSpecialtyService;
//
//    public DoctorSpecialtyController(DoctorSpecialtyService doctorSpecialtyService) {
//        this.doctorSpecialtyService = doctorSpecialtyService;
//    }
//
//    @Operation(
//            summary = "Create Doctor Specialty REST API",
//            description = "Create Doctor Specialty REST API is used to save doctor specialty into database"
//    )
//    @ApiResponse(
//            responseCode = "201",
//            description = "Http Status 201 CREATED"
//    )
//    @PostMapping("/doctors/{doctorId}/specialties/{specialityId}")
//    public ResponseEntity<DoctorSpecialtyDto> createDoctorSpecialty(@RequestBody @Valid DoctorSpecialtyDto doctorSpecialtyDto,
//                                                                    @PathVariable Long doctorId,
//                                                                    @PathVariable Long specialityId
//    ) {
//        return new ResponseEntity<>(doctorSpecialtyService.createDoctorSpecialty(doctorSpecialtyDto, doctorId, specialityId), HttpStatus.CREATED);
//    }
//
//    @Operation(
//            summary = "Get Doctor Specialty By Id REST API",
//            description = "Get Doctor Specialty By Id REST API is used to get a single doctor specialty from the database"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Http Status 200 SUCCESS"
//    )
//    @GetMapping("/{doctorSpecialtyId}")
//    public ResponseEntity<DoctorSpecialtyDto> getDoctorSpecialtyById(@PathVariable Long doctorSpecialtyId) {
//        return ResponseEntity.ok(doctorSpecialtyService.getDoctorSpecialtyById(doctorSpecialtyId));
//    }
//
//    @Operation(
//            summary = "Get All Doctor Specialties REST API",
//            description = "Get All Doctor Specialties REST API is used to fetch all the doctor specialties from the database"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Http Status 200 SUCCESS"
//    )
//    @GetMapping("")
//    public ResponseEntity<DoctorSpecialtyResponse> getAllDoctorsSpecialties
//            (
//                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
//                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
//                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
//                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
//            ) {
//        return ResponseEntity.ok(doctorSpecialtyService.getAllDoctorSpecialties(pageNo, pageSize, sortBy, sortDir));
//    }
//
//    @Operation(
//            summary = "Get All Doctor Specialties By Doctor Id REST API",
//            description = "Get All Doctor Specialties By Doctor Id REST API is used to fetch all the doctor specialties from the database by doctor id"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Http Status 200 SUCCESS"
//    )
//    @GetMapping("doctors/{doctorId}")
//    public ResponseEntity<DoctorSpecialtyResponse> getAllDoctorsSpecialtiesByDoctorId
//            (
//                    @PathVariable Long doctorId,
//                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
//                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
//                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
//                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
//            ) {
//        return ResponseEntity.ok(doctorSpecialtyService.getAllDoctorSpecialtiesByDoctorId(doctorId, pageNo, pageSize, sortBy, sortDir));
//    }
//
//    @Operation(
//            summary = "Get All Doctor Specialties By Specialty Id REST API",
//            description = "Get All Doctor Specialties By Specialty Id REST API is used to fetch all the doctor specialties from the database by specialty id"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Http Status 200 SUCCESS"
//    )
//    @GetMapping("specialties/{specialtyId}")
//    public ResponseEntity<DoctorSpecialtyResponse> getAllDoctorsSpecialtiesBySpecialtyId
//            (
//                    @PathVariable Long specialtyId,
//                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
//                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
//                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
//                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
//            ) {
//        return ResponseEntity.ok(doctorSpecialtyService.getAllDoctorSpecialtiesBySpecialtyId(specialtyId, pageNo, pageSize, sortBy, sortDir));
//    }
//
//    @Operation(
//            summary = "Update Doctor Specialty REST API",
//            description = "Update Doctor Specialty REST API is used to update a particular doctor specialty in the database"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Http Status 200 SUCCESS"
//    )
//    @PutMapping("/{doctorSpecialtyId}")
//    public ResponseEntity<DoctorSpecialtyDto> updateDoctorSpecialtyById(@PathVariable Long doctorSpecialtyId, @RequestBody @Valid DoctorSpecialtyDto doctorSpecialtyDto) {
//        return ResponseEntity.ok(doctorSpecialtyService.updateDoctorSpecialtyById(doctorSpecialtyDto, doctorSpecialtyId));
//    }
//
//    @Operation(
//            summary = "Delete Doctor Specialty REST API",
//            description = "Delete Doctor Specialty REST API is used to delete doctor specialty from the database"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Http Status 200 SUCCESS"
//    )
//    @DeleteMapping("/{doctorSpecialtyId}")
//    public ResponseEntity<String> deleteDoctorSpecialtyById(@PathVariable Long doctorSpecialtyId) {
//        doctorSpecialtyService.deleteDoctorSpecialtyById(doctorSpecialtyId);
//        return ResponseEntity.ok(AppConstants.SUCCESSFULLY_DELETED_MESSAGE);
//    }
//
//}
