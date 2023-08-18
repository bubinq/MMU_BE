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
import team.yellow.docconnect.payload.dto.StateDto;
import team.yellow.docconnect.payload.response.StateResponse;
import team.yellow.docconnect.service.StateService;
import team.yellow.docconnect.utils.AppConstants;
import team.yellow.docconnect.utils.Messages;

@CrossOrigin(maxAge = 999999999)
@RestController
@RequestMapping("/api/v1/states")
@Tag(name = "CRUD REST APIs for State Resource")
public class StateController {

    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @Operation(
            summary = "Create State REST API",
            description = "Create State REST API is used to save state into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<StateDto> createState(@RequestBody @Valid StateDto stateDto) {
        return new ResponseEntity<>(stateService.createState(stateDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get State By Id REST API",
            description = "Get State By Id REST API is used to get a single state from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("{stateId}")
    public ResponseEntity<StateDto> getStateById(@PathVariable Long stateId) {
        return ResponseEntity.ok(stateService.getStateById(stateId));
    }

    @Operation(
            summary = "Get All States REST API",
            description = "Get All States REST API is used to fetch all the states from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("")
    public ResponseEntity<StateResponse> getAllStates
            (
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ) {
        return ResponseEntity.ok(stateService.getAllStates(pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Update State REST API",
            description = "Update State REST API is used to update a particular state in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{stateId}")
    public ResponseEntity<StateDto> updateStateById(@PathVariable Long stateId, @RequestBody @Valid StateDto stateDto) {
        return ResponseEntity.ok(stateService.updateStateById(stateDto, stateId));
    }

    @Operation(
            summary = "Delete State REST API",
            description = "Delete State REST API is used to delete state from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{stateId}")
    public ResponseEntity<String> deleteStateById(@PathVariable Long stateId) {
        stateService.deleteStateId(stateId);
        return ResponseEntity.ok(Messages.SUCCESSFULLY_DELETED_STATE + stateId);
    }
}
