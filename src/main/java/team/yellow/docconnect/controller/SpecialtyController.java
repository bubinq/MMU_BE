package team.yellow.docconnect.controller;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.yellow.docconnect.payload.dto.SpecialtyDto;
import team.yellow.docconnect.payload.response.SpecialtyResponse;
import team.yellow.docconnect.service.SpecialtyService;
import team.yellow.docconnect.utils.AppConstants;

@RestController
@RequestMapping("api/v1/specialties")
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }


    @PostMapping
    public ResponseEntity<SpecialtyDto> createSpecialty(@Valid @RequestBody SpecialtyDto specialtyDto) {
        return new ResponseEntity<>(specialtyService.createSpecialty(specialtyDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<SpecialtyDto> getSpecialtyById(@PathVariable Long id) {
        return ResponseEntity.ok(specialtyService.getSpecialtyById(id));
    }

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

    @PutMapping("{id}")
    public ResponseEntity<SpecialtyDto> updateSpecialtyById(@RequestBody @Valid SpecialtyDto specialtyDto, @PathVariable Long id) {
        return ResponseEntity.ok(specialtyService.updateSpecialtyById(id, specialtyDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSpecialtyById(@PathVariable Long id) {
        specialtyService.deleteSpecialtyById(id);
        return ResponseEntity.ok(AppConstants.SUCCESSFULLY_DELETED_MESSAGE);
    }
}