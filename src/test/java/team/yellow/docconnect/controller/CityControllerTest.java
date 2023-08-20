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
import team.yellow.docconnect.payload.dto.CityDto;
import team.yellow.docconnect.payload.response.CityResponse;
import team.yellow.docconnect.service.CityService;
import team.yellow.docconnect.utils.AppConstants;

@RunWith(MockitoJUnitRunner.class)
class CityControllerTest {
    @InjectMocks
    private CityController cityController;

    @Mock
    private CityService cityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCityAndReturnCreatedResponseWhenProvidedValidStateId() {
        Long validStateId = 1L;
        CityDto cityToInsert = new CityDto(1L, "Varna", "State");
        Mockito.when(cityService.createCity(cityToInsert, validStateId)).thenReturn(cityToInsert);
        ResponseEntity<CityDto> receivedResponse = cityController.createCity(cityToInsert, validStateId);
        Assert.assertEquals(HttpStatus.CREATED, receivedResponse.getStatusCode());
        Assert.assertEquals(cityToInsert, receivedResponse.getBody());
    }

    @Test
    void shouldNotCreateCityWhenProvidedInvalidStateId() {
        Long validStateId = 1L;
        Long invalidStateId = 2L;
        CityDto cityToInsert = new CityDto(1L, "Varna", "State");
        Mockito.when(cityService.createCity(cityToInsert, validStateId)).thenReturn(cityToInsert);
        ResponseEntity<CityDto> receivedResponse = cityController.createCity(cityToInsert, invalidStateId);
        Assert.assertNotEquals(cityToInsert, receivedResponse.getBody());
    }

    @Test
    void shouldReturnDtoAndOKResponseWhenProvidedValidId() {
        Long validCityId = 1L;
        CityDto expectedCity = new CityDto(validCityId, "Varna", "State");
        Mockito.when(cityService.getCityById(validCityId)).thenReturn(expectedCity);
        ResponseEntity<CityDto> receivedResponse = cityController.getCityById(validCityId);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedCity, receivedResponse.getBody());
    }


    @Test
    void shouldNotReturnDtoWhenProvidedInvalidId() {
        Long validCityId = 1L;
        Long invalidCityId = 2L;
        CityDto expectedCity = new CityDto(validCityId, "Varna", "State");
        Mockito.when(cityService.getCityById(validCityId)).thenReturn(expectedCity);
        ResponseEntity<CityDto> receivedResponse = cityController.getCityById(invalidCityId);
        Assert.assertNotEquals(expectedCity, receivedResponse.getBody());
    }


    @Test
    void shouldReturnAllCitiesPaginated() {
        CityResponse expectedCities = new CityResponse();
        Mockito.when(cityService
                .getAllCities
                        (
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                                AppConstants.DEFAULT_SORT_BY,
                                AppConstants.DEFAULT_SORT_DIRECTION
                        )
        ).thenReturn(expectedCities);

        ResponseEntity<CityResponse> receivedResponse = cityController.getAllCities
                (
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedCities, receivedResponse.getBody());
    }
    @Test
    void shouldReturnAllCitiesInStateWhenProvidedValidStateId() {
        Long validStateId = 1L;
        CityResponse expectedCities = new CityResponse();
        Mockito.when(cityService
                .getAllCitiesByStateId
                        (
                                validStateId,
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                                AppConstants.DEFAULT_SORT_BY,
                                AppConstants.DEFAULT_SORT_DIRECTION
                        )
        ).thenReturn(expectedCities);

        ResponseEntity<CityResponse> receivedResponse = cityController.getAllCitiesByState
                (
                        validStateId,
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedCities, receivedResponse.getBody());

    }


    @Test
    void shouldNotReturnAllCitiesInStateWhenProvidedInvalidStateId() {
        Long validStateId = 1L;
        Long invalidStateId = 2L;
        CityResponse expectedCities = new CityResponse();
        Mockito.when(cityService
                .getAllCitiesByStateId
                        (
                                validStateId,
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                                AppConstants.DEFAULT_SORT_BY,
                                AppConstants.DEFAULT_SORT_DIRECTION
                        )
        ).thenReturn(expectedCities);

        ResponseEntity<CityResponse> receivedResponse = cityController.getAllCitiesByState
                (
                        invalidStateId,
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );
        Assert.assertNotEquals(expectedCities, receivedResponse.getBody());

    }
    @Test
    void shouldUpdateDtoAndReturnOKWhenProvidedValidId() {
        Long validCityId = 1L;
        CityDto updatedCity = new CityDto(validCityId, "Varna", "State");
        Mockito.when(cityService.updateCityById(updatedCity, validCityId)).thenReturn(updatedCity);
        ResponseEntity<CityDto> receivedResponse = cityController.updateCityById(updatedCity, validCityId);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(updatedCity, receivedResponse.getBody());
    }

    @Test
    void shouldNotUpdateDtoWhenProvidedInvalidId() {
        Long validCityId = 1L;
        Long invalidCityId = 2L;
        CityDto updatedCity = new CityDto(validCityId, "Varna", "State");
        Mockito.when(cityService.updateCityById(updatedCity, validCityId)).thenReturn(updatedCity);
        ResponseEntity<CityDto> receivedResponse = cityController.updateCityById(updatedCity, invalidCityId);
        Assert.assertNotEquals(updatedCity, receivedResponse.getBody());
    }

    @Test
    void shouldDeleteCityAndReturnOKResponse() {
        Long validCityId = 1L;
        CityDto cityToDelete = new CityDto(validCityId, "Varna", "State");
        cityService.createCity(cityToDelete, 1L);
        ResponseEntity<String> receivedResponse = cityController.deleteCityById(validCityId);
        Assertions.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assertions.assertEquals("Successfully deleted!", receivedResponse.getBody());

    }
}