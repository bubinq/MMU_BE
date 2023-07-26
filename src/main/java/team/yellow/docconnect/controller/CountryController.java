package team.yellow.docconnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.yellow.docconnect.payload.dto.CountryDto;
import team.yellow.docconnect.payload.response.CountryResponse;
import team.yellow.docconnect.service.CountryService;
import team.yellow.docconnect.utils.AppConstants;
@RestController
@RequestMapping("/api/v1/countries")
@Tag(name = "CRUD REST APIs for Country Resource")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Operation(
            summary = "Create Country REST API",
            description = "Create Country REST API is used to save country into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("")
    public ResponseEntity<CountryDto> createCountry(@RequestBody @Valid CountryDto countryDto) {
        return new ResponseEntity<>(countryService.createCountry(countryDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Country By Id REST API",
            description = "Get Country By Id REST API is used to get a single country from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("{countryId}")
    public ResponseEntity<CountryDto> getCountryById(@PathVariable Long countryId) {
        return ResponseEntity.ok(countryService.getCountryById(countryId));
    }

    @Operation(
            summary = "Get All Countries REST API",
            description = "Get All Countries REST API is used to fetch all the countries from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("")
    public ResponseEntity<CountryResponse> getAllCountries
            (
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ) {
        return ResponseEntity.ok(countryService.getAllCountries(pageNo, pageSize, sortBy, sortDir));
    }

    @Operation(
            summary = "Update Country REST API",
            description = "Update Country REST API is used to update a particular country in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PutMapping("{countryId}")
    public ResponseEntity<CountryDto> updateCountryById(@PathVariable Long countryId, @RequestBody @Valid CountryDto countryDto) {
        return ResponseEntity.ok(countryService.updateCountryById(countryDto, countryId));
    }

    @Operation(
            summary = "Delete Country REST API",
            description = "Delete Country REST API is used to delete country from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @DeleteMapping("{countryId}")
    public ResponseEntity<String> deleteCountryById(@PathVariable Long countryId) {
        countryService.deleteCountryId(countryId);
        return ResponseEntity.ok("Successfully deleted country with id: " + countryId);
    }
}
