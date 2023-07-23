package team.yellow.docconnect.controller;

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
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("")
    public ResponseEntity<CountryDto> createCountry(@RequestBody @Valid CountryDto countryDto){
        return new ResponseEntity<>(countryService.createCountry(countryDto), HttpStatus.CREATED);
    }

    @GetMapping("{countryId}")
    public ResponseEntity<CountryDto> getCountryById(@PathVariable Long countryId){
        return ResponseEntity.ok(countryService.getCountryById(countryId));
    }

    @GetMapping("")
    public ResponseEntity<CountryResponse> getAllCountries
            (
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            )
    {
        return ResponseEntity.ok(countryService.getAllCountries(pageNo, pageSize, sortBy, sortDir));
    }

    @PutMapping("{countryId}")
    public ResponseEntity<CountryDto> updateCountryById(@PathVariable Long countryId, @RequestBody @Valid CountryDto countryDto) {
        return ResponseEntity.ok(countryService.updateCountryById(countryDto, countryId));
    }

    @DeleteMapping("{countryId}")
    public ResponseEntity<String> deleteCountryById(@PathVariable Long countryId){
        countryService.deleteCountryId(countryId);
        return ResponseEntity.ok("Successfully deleted country with id: "+ countryId);
    }
}
