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
import team.yellow.docconnect.payload.dto.CountryDto;
import team.yellow.docconnect.payload.response.CountryResponse;
import team.yellow.docconnect.service.CountryService;
import team.yellow.docconnect.utils.AppConstants;

@RunWith(MockitoJUnitRunner.class)
class CountryControllerTest {

    @InjectMocks
    private CountryController countryController;

    @Mock
    private CountryService countryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCountryAndReturnCreatedResponse() {
        Long countryId = 1L;
        CountryDto countryToInsert = new CountryDto(countryId, "Bulgaria");
        Mockito.when(countryService.createCountry(countryToInsert)).thenReturn(countryToInsert);
        ResponseEntity<CountryDto> receivedResponse = countryController.createCountry(countryToInsert);
        Assert.assertEquals(HttpStatus.CREATED, receivedResponse.getStatusCode());
        Assert.assertEquals(countryToInsert, receivedResponse.getBody());
    }

    @Test
    void shouldReturnOKResponseAndProperCountryWhenProvidedValidId() {
        Long validCountryId = 1L;
        CountryDto expectedCountry = new CountryDto(validCountryId, "Bulgaria");
        Mockito.when(countryService.getCountryById(validCountryId)).thenReturn(expectedCountry);
        ResponseEntity<CountryDto> receivedResponse = countryController.getCountryById(validCountryId);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedCountry, receivedResponse.getBody());
    }

    @Test
    void shouldNotReturnProperCountryWhenProvidedInvalidId() {
        Long validCountryId = 1L;
        Long invalidCountryId = 2L;
        CountryDto expectedCountry = new CountryDto(validCountryId, "Bulgaria");
        Mockito.when(countryService.getCountryById(validCountryId)).thenReturn(expectedCountry);
        ResponseEntity<CountryDto> receivedResponse = countryController.getCountryById(invalidCountryId);
        Assert.assertNotEquals(expectedCountry, receivedResponse.getBody());
    }

    @Test
    void shouldReturnOKResponseAndAllCountriesPaginated() {
        CountryResponse expectedCountries = new CountryResponse();

        Mockito.when(countryService
                .getAllCountries
                        (
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                                AppConstants.DEFAULT_SORT_BY,
                                AppConstants.DEFAULT_SORT_DIRECTION
                        )
        ).thenReturn(expectedCountries);

        ResponseEntity<CountryResponse> receivedResponse = countryController.getAllCountries
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
    void shouldUpdateCountryAndReturnOKResponseWhenProvidedValidId() {
        Long validId = 1L;
        CountryDto expectedUpdatedCountry = new CountryDto(validId, "Bulgaria");
        Mockito.when(countryService.updateCountryById(expectedUpdatedCountry,validId)).thenReturn(expectedUpdatedCountry);
        ResponseEntity<CountryDto> receivedResponse = countryController.updateCountryById(validId, expectedUpdatedCountry);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedUpdatedCountry, receivedResponse.getBody());
    }

    @Test
    void shouldNotUpdateCountryWhenProvidedInvalidId() {
        Long validId = 1L;
        Long invalidId = 2L;
        CountryDto expectedUpdatedCountry = new CountryDto(validId, "Bulgaria");
        Mockito.when(countryService.updateCountryById(expectedUpdatedCountry,validId)).thenReturn(expectedUpdatedCountry);
        ResponseEntity<CountryDto> receivedResponse = countryController.updateCountryById(invalidId, expectedUpdatedCountry);
        Assert.assertNotEquals(expectedUpdatedCountry, receivedResponse.getBody());
    }

    @Test
    void shouldDeleteCountryAndReturnOKResponse() {
        Long countryId = 1L;
        CountryDto countryToInsert = new CountryDto(countryId, "Bulgaria");
        countryService.createCountry(countryToInsert);
        ResponseEntity<String> receivedResponse = countryController.deleteCountryById(countryId);
        Assertions.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assertions.assertEquals("Successfully deleted country with id: "+ countryId, receivedResponse.getBody());
    }
}