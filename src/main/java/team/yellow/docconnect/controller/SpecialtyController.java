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
import team.yellow.docconnect.payload.dto.SpecialtyDto;
import team.yellow.docconnect.payload.response.SpecialtyResponse;
import team.yellow.docconnect.service.SpecialtyService;
import team.yellow.docconnect.utils.AppConstants;
import team.yellow.docconnect.utils.Messages;

@CrossOrigin(maxAge = 999999999)
@RestController
@RequestMapping("api/v1/specialties")
@Tag(name = "CRUD REST APIs for Specialty Resource")
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Operation(
            summary = "Create Specialty REST API",
            description = "Create Specialty REST API is used to save specialty into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SpecialtyDto> createSpecialty(@Valid @RequestBody SpecialtyDto specialtyDto) {
        return new ResponseEntity<>(specialtyService.createSpecialty(specialtyDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Specialty By Id REST API",
            description = "Get Specialty By Id REST API is used to get a single specialty from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<SpecialtyDto> getSpecialtyById(@PathVariable Long id) {
        return ResponseEntity.ok(specialtyService.getSpecialtyById(id));
    }

    @Operation(
            summary = "Get All Specialties REST API",
            description = "Get All Specialties REST API is used to fetch all the specialties from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<SpecialtyResponse> getAllSpecialties
            (
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ) {
        return ResponseEntity.ok(specialtyService.getAllSpecialties(pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Update Specialty REST API",
            description = "Update Specialty REST API is used to update a particular specialty in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<SpecialtyDto> updateSpecialtyById(@RequestBody @Valid SpecialtyDto specialtyDto, @PathVariable Long id) {
        return ResponseEntity.ok(specialtyService.updateSpecialtyById(id, specialtyDto));
    }

    @Operation(
            summary = "Delete Specialty REST API",
            description = "Delete Specialty REST API is used to delete specialty from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSpecialtyById(@PathVariable Long id) {
        specialtyService.deleteSpecialtyById(id);
        return ResponseEntity.ok(Messages.SUCCESSFULLY_DELETED_MESSAGE);
    }
}