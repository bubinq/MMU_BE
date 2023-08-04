package team.yellow.docconnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.yellow.docconnect.payload.dto.CityDto;
import team.yellow.docconnect.payload.response.CityResponse;
import team.yellow.docconnect.service.CityService;
import team.yellow.docconnect.utils.AppConstants;
@CrossOrigin(maxAge = 999999999)
@RestController
@RequestMapping("api/v1/")
@Tag(name = "CRUD REST APIs for City Resource")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @Operation(
            summary = "Create City REST API",
            description = "Create City REST API is used to save city into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("states/{stateId}/cities")
    public ResponseEntity<CityDto> createCity(@Valid @RequestBody CityDto cityDto, @PathVariable Long stateId) {
        return new ResponseEntity<>(cityService.createCity(cityDto, stateId), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get City By Id REST API",
            description = "Get City By Id REST API is used to get a single city from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("cities/{cityId}")
    public ResponseEntity<CityDto> getCityById(@PathVariable Long cityId) {
        return ResponseEntity.ok(cityService.getCityById(cityId));
    }

    @Operation(
            summary = "Get All Cities REST API",
            description = "Get All Cities REST API is used to fetch all the cities from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("cities")
    public ResponseEntity<CityResponse> getAllCities
            (
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = "999999", required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ) {
        return ResponseEntity.ok(cityService.getAllCities(pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Get All Cities By State Id REST API",
            description = "Get All Cities By State Id REST API is used to fetch all the cities from the database by state id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("states/{stateId}/cities")
    public ResponseEntity<CityResponse> getAllCitiesByState
            (
                    @PathVariable Long stateId,
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = "999999", required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ) {
        return ResponseEntity.ok(cityService.getAllCitiesByStateId(stateId, pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Update City REST API",
            description = "Update City REST API is used to update a particular city in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PutMapping("cities/{cityId}")
    public ResponseEntity<CityDto> updateCityById(@RequestBody @Valid CityDto cityDto, @PathVariable Long cityId) {
        return ResponseEntity.ok(cityService.updateCityById(cityDto, cityId));
    }

    @Operation(
            summary = "Delete City REST API",
            description = "Delete City REST API is used to delete city from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @DeleteMapping("cities/{cityId}")
    public ResponseEntity<String> deleteCityById(@PathVariable Long cityId) {
        cityService.deleteCityById(cityId);
        return ResponseEntity.ok("Successfully deleted");
    }
}
