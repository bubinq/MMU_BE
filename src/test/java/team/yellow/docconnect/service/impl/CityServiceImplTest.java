package team.yellow.docconnect.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import team.yellow.docconnect.payload.dto.CityDto;
import team.yellow.docconnect.payload.response.CityResponse;
import team.yellow.docconnect.service.CityService;
import team.yellow.docconnect.utils.AppConstants;
@RunWith(MockitoJUnitRunner.class)
class CityServiceImplTest {

    @Mock
    private CityService cityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCityWhenProvidedValidStateId() {
        Long validStateId = 1L;
        CityDto cityToInsert = new CityDto(1L, "Varna", "State");
        Mockito.when(cityService.createCity(cityToInsert, validStateId)).thenReturn(cityToInsert);
        CityDto receivedDto = cityService.createCity(cityToInsert, validStateId);
        Assert.assertEquals(cityToInsert, receivedDto);
    }

    @Test
    void shouldNotCreateCityWhenProvidedInvalidStateId() {
        Long validStateId = 1L;
        Long invalidStateId = 2L;
        CityDto cityToInsert = new CityDto(1L, "Varna", "State");
        Mockito.when(cityService.createCity(cityToInsert, validStateId)).thenReturn(cityToInsert);
        CityDto receivedDto = cityService.createCity(cityToInsert, invalidStateId);
        Assert.assertNotEquals(cityToInsert, receivedDto);
    }

    @Test
    void shouldReturnDtoWhenProvidedValidId() {
        Long validCityId = 1L;
        CityDto expectedCity = new CityDto(validCityId, "Varna", "State");
        Mockito.when(cityService.getCityById(validCityId)).thenReturn(expectedCity);
        CityDto receivedDto = cityService.getCityById(validCityId);
        Assert.assertEquals(expectedCity, receivedDto);
    }


    @Test
    void shouldNotReturnDtoWhenProvidedInvalidId() {
        Long validCityId = 1L;
        Long invalidCityId = 2L;
        CityDto expectedCity = new CityDto(validCityId, "Varna", "State");
        Mockito.when(cityService.getCityById(validCityId)).thenReturn(expectedCity);
        CityDto receivedDto = cityService.getCityById(invalidCityId);
        Assert.assertNotEquals(expectedCity, receivedDto);
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

        CityResponse receivedCities = cityService.getAllCities
                (
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );
        Assert.assertEquals(expectedCities, receivedCities);
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

       CityResponse receivedCities = cityService.getAllCitiesByStateId
                (
                        validStateId,
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );
        Assert.assertEquals(expectedCities, receivedCities);

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

        CityResponse receivedCities = cityService.getAllCitiesByStateId
                (
                        invalidStateId,
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );
        Assert.assertNotEquals(expectedCities,receivedCities);

    }
    @Test
    void shouldUpdateDtoWhenProvidedValidId() {
        Long validCityId = 1L;
        CityDto updatedCity = new CityDto(validCityId, "Varna", "State");
        Mockito.when(cityService.updateCityById(updatedCity, validCityId)).thenReturn(updatedCity);
        CityDto receivedDto = cityService.updateCityById(updatedCity, validCityId);
        Assert.assertEquals(updatedCity, receivedDto);
    }

    @Test
    void shouldNotUpdateDtoWhenProvidedInvalidId() {
        Long validCityId = 1L;
        Long invalidCityId = 2L;
        CityDto updatedCity = new CityDto(validCityId, "Varna", "State");
        Mockito.when(cityService.updateCityById(updatedCity, validCityId)).thenReturn(updatedCity);
        CityDto receivedDto = cityService.updateCityById(updatedCity, invalidCityId);
        Assert.assertNotEquals(updatedCity, receivedDto);
    }


}