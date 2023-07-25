package team.yellow.docconnect.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.yellow.docconnect.payload.dto.CityDto;
import team.yellow.docconnect.payload.response.CityResponse;
import team.yellow.docconnect.service.CityService;
import team.yellow.docconnect.utils.AppConstants;

@RestController
@RequestMapping("api/v1/")

public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("countries/{countryId}/cities")
    public ResponseEntity<CityDto> createCity(@Valid @RequestBody CityDto cityDto, @PathVariable Long countryId){
        return new ResponseEntity<>(cityService.createCity(cityDto, countryId), HttpStatus.CREATED);
    }

    @GetMapping("cities/{cityId}")
    public ResponseEntity<CityDto> getCityById(@PathVariable Long cityId){
        return ResponseEntity.ok(cityService.getCityById(cityId));
    }

    @GetMapping("cities")
    public ResponseEntity<CityResponse> getAllCities
            (
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            )
         {
            return ResponseEntity.ok(cityService.getAllCities(pageNo, pageSize, sortBy, sortDir));
         }

    @GetMapping("countries/{countryId}/cities")
    public ResponseEntity<CityResponse> getAllCitiesByCountry
            (
                    @PathVariable Long countryId,
                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            )
    {
        return ResponseEntity.ok(cityService.getAllCitiesByCountryId(countryId, pageNo, pageSize, sortBy, sortDir));
    }
    @PutMapping("cities/{cityId}")
    public ResponseEntity<CityDto> updateCityById(@RequestBody @Valid CityDto cityDto, @PathVariable Long cityId){
        return ResponseEntity.ok(cityService.updateCityById(cityDto, cityId));
    }

    @DeleteMapping("cities/{cityId}")
    public ResponseEntity<String> deleteCityById(@PathVariable Long cityId){
        cityService.deleteCityById(cityId);
        return ResponseEntity.ok("Successfully deleted");
    }
}
