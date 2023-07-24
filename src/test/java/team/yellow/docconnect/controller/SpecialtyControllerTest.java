package team.yellow.docconnect.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import team.yellow.docconnect.payload.dto.SpecialtyDto;
import team.yellow.docconnect.payload.response.SpecialtyResponse;
import team.yellow.docconnect.service.SpecialtyService;
import team.yellow.docconnect.utils.AppConstants;

@RunWith(MockitoJUnitRunner.class)
class SpecialtyControllerTest {

    @InjectMocks
    private SpecialtyController specialtyController;

    @Mock
    private SpecialtyService specialtyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateSpecialtyAndReturnCreatedResponse() {
        Long specialtyId = 1L;
        SpecialtyDto specialtyToInsert =new SpecialtyDto(specialtyId, "specialty test","https://www.images.com/example.png");
        Mockito.when(specialtyService.createSpecialty(specialtyToInsert)).thenReturn(specialtyToInsert);
        ResponseEntity<SpecialtyDto> receivedResponse = specialtyController.createSpecialty(specialtyToInsert);
        Assert.assertEquals(HttpStatus.CREATED, receivedResponse.getStatusCode());
        Assert.assertEquals(specialtyToInsert, receivedResponse.getBody());
    }

    @Test
    void shouldReturnOKResponseAndProperSpecialtyWhenProvidedValidId() {
        Long validSpecialtyId = 1L;
        SpecialtyDto expectedSpecialty = new SpecialtyDto(validSpecialtyId, "specialty test","https://www.images.com/example.png");
        Mockito.when(specialtyService.getSpecialtyById(validSpecialtyId)).thenReturn(expectedSpecialty);
        ResponseEntity<SpecialtyDto> receivedResponse = specialtyController.getSpecialtyById(validSpecialtyId);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedSpecialty, receivedResponse.getBody());
    }

    @Test
    void shouldNotReturnProperSpecialtyWhenProvidedInvalidId() {
        Long validSpecialtyId = 1L;
        Long invalidSpecialtyId = 2L;
        SpecialtyDto expectedSpecialty = new SpecialtyDto(validSpecialtyId, "specialty test","https://www.images.com/example.png");
        Mockito.when(specialtyService.getSpecialtyById(validSpecialtyId)).thenReturn(expectedSpecialty);
        ResponseEntity<SpecialtyDto> receivedResponse = specialtyController.getSpecialtyById(invalidSpecialtyId);
        Assert.assertNotEquals(expectedSpecialty, receivedResponse.getBody());
    }

    @Test
    void shouldReturnOKResponseAndAllCountriesPaginated() {
        SpecialtyResponse expectedCountries = new SpecialtyResponse();

        Mockito.when(specialtyService
                .getAllSpecialties
                        (
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                                AppConstants.DEFAULT_SORT_BY,
                                AppConstants.DEFAULT_SORT_DIRECTION
                        )
        ).thenReturn(expectedCountries);

        ResponseEntity<SpecialtyResponse> receivedResponse = specialtyController.getAllSpecialties
                (
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedCountries, receivedResponse.getBody());
    }

    @Test
    void shouldUpdateSpecialtyAndReturnOKResponseWhenProvidedValidId() {
        Long validId = 1L;
        SpecialtyDto expectedUpdatedSpecialty = new SpecialtyDto(validId, "specialty test","https://www.images.com/example.png");
        Mockito.when(specialtyService.updateSpecialtyById(validId,expectedUpdatedSpecialty)).thenReturn(expectedUpdatedSpecialty);
        ResponseEntity<SpecialtyDto> receivedResponse = specialtyController.updateSpecialtyById(expectedUpdatedSpecialty,validId);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedUpdatedSpecialty, receivedResponse.getBody());
    }

    @Test
    void shouldNotUpdateSpecialtyWhenProvidedInvalidId() {
        Long validId = 1L;
        Long invalidId = 2L;
        SpecialtyDto expectedUpdatedSpecialty = new SpecialtyDto(validId, "specialty test","https://www.images.com/example.png");
        Mockito.when(specialtyService.updateSpecialtyById(validId, expectedUpdatedSpecialty)).thenReturn(expectedUpdatedSpecialty);
        ResponseEntity<SpecialtyDto> receivedResponse = specialtyController.updateSpecialtyById(expectedUpdatedSpecialty,invalidId);
        Assert.assertNotEquals(expectedUpdatedSpecialty, receivedResponse.getBody());
    }

    @Test
    void shouldDeleteSpecialtyAndReturnOKResponse() {
        Long specialtyId = 1L;
        SpecialtyDto specialtyToInsert = new SpecialtyDto(specialtyId, "specialty test","https://www.images.com/example.png");
        specialtyService.createSpecialty(specialtyToInsert);
        ResponseEntity<String> receivedResponse = specialtyController.deleteSpecialtyById(specialtyId);
        Assertions.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assertions.assertEquals(AppConstants.SUCCESSFULLY_DELETED_MESSAGE, receivedResponse.getBody());
    }
}