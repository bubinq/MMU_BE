package team.yellow.docconnect.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import team.yellow.docconnect.payload.dto.CountryDto;
import team.yellow.docconnect.payload.response.CountryResponse;
import team.yellow.docconnect.service.CountryService;
import team.yellow.docconnect.utils.AppConstants;



@RunWith(MockitoJUnitRunner.class)
class CountryServiceImplTest {


    @Mock
    private CountryService countryService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCountryAndReturnCountryDto() {
        Long countryId = 1L;
        CountryDto countryToInsert = new CountryDto(countryId, "Bulgaria");
        Mockito.when(countryService.createCountry(countryToInsert)).thenReturn(countryToInsert);
        CountryDto createdCountry = countryService.createCountry(countryToInsert);
        Assert.assertEquals(countryToInsert, createdCountry);
    }

    @Test
    void shouldReturnCountryDtoWhenProvidedValidId() {
        Long validId = 1L;
        CountryDto expectedCountry = new CountryDto(validId, "Bulgaria");
        Mockito.when(countryService.getCountryById(validId)).thenReturn(expectedCountry);
        CountryDto foundCountry = countryService.getCountryById(validId);
        Assert.assertEquals(expectedCountry, foundCountry);
    }

    @Test
    void shouldNotReturnExpectedCountryDtoWhenProvidedInvalidId() {
        Long validId = 1L;
        Long invalidId = 2L;
        CountryDto expectedCountry = new CountryDto(validId, "Bulgaria");
        Mockito.when(countryService.getCountryById(validId)).thenReturn(expectedCountry);
        CountryDto foundCountry = countryService.getCountryById(invalidId);
        Assert.assertNotEquals(expectedCountry, foundCountry);
    }

    @Test
    void shouldReturnAllCountriesPaginated() {
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

        CountryResponse foundCountries = countryService.getAllCountries
                (
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );

        Assert.assertEquals(expectedCountries, foundCountries);
    }

    @Test
    void shouldUpdateCountryAndReturnCountryDtoWhenProvidedValidId() {
        Long validId = 1L;
        CountryDto expectedUpdatedCountry = new CountryDto(validId, "Bulgaria");
        Mockito.when(countryService.updateCountryById(expectedUpdatedCountry,validId)).thenReturn(expectedUpdatedCountry);
        CountryDto updatedCountry = countryService.updateCountryById(expectedUpdatedCountry, validId);
        Assert.assertEquals(expectedUpdatedCountry, updatedCountry);
    }

    @Test
    void shouldNotUpdateCountryWhenProvidedInValidId() {
        Long validId = 1L;
        Long invalidId = 2L;
        CountryDto expectedUpdatedCountry = new CountryDto(validId, "Bulgaria");
        Mockito.when(countryService.updateCountryById(expectedUpdatedCountry,validId)).thenReturn(expectedUpdatedCountry);
        CountryDto updatedCountry = countryService.updateCountryById(expectedUpdatedCountry, invalidId);
        Assert.assertNotEquals(expectedUpdatedCountry, updatedCountry);
    }
}