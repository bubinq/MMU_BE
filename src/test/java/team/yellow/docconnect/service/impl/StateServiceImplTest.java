package team.yellow.docconnect.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import team.yellow.docconnect.payload.dto.StateDto;
import team.yellow.docconnect.payload.response.StateResponse;
import team.yellow.docconnect.service.StateService;
import team.yellow.docconnect.utils.AppConstants;



@RunWith(MockitoJUnitRunner.class)
class StateServiceImplTest {


    @Mock
    private StateService stateService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateStateAndReturnCountryDto() {
        Long countryId = 1L;
        StateDto stateToInsert = new StateDto(countryId, "Bulgaria");
        Mockito.when(stateService.createState(stateToInsert)).thenReturn(stateToInsert);
        StateDto createdCountry = stateService.createState(stateToInsert);
        Assert.assertEquals(stateToInsert, createdCountry);
    }

    @Test
    void shouldReturnStateDtoWhenProvidedValidId() {
        Long validId = 1L;
        StateDto expectedState = new StateDto(validId, "Bulgaria");
        Mockito.when(stateService.getStateById(validId)).thenReturn(expectedState);
        StateDto foundCountry = stateService.getStateById(validId);
        Assert.assertEquals(expectedState, foundCountry);
    }

    @Test
    void shouldNotReturnExpectedStateDtoWhenProvidedInvalidId() {
        Long validId = 1L;
        Long invalidId = 2L;
        StateDto expectedState = new StateDto(validId, "Bulgaria");
        Mockito.when(stateService.getStateById(validId)).thenReturn(expectedState);
        StateDto foundCountry = stateService.getStateById(invalidId);
        Assert.assertNotEquals(expectedState, foundCountry);
    }

    @Test
    void shouldReturnAllStatesPaginated() {
        StateResponse expectedStates = new StateResponse();
        Mockito.when(stateService
                .getAllStates
                        (
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                                AppConstants.DEFAULT_SORT_BY,
                                AppConstants.DEFAULT_SORT_DIRECTION
                        )
        ).thenReturn(expectedStates);

        StateResponse foundCountries = stateService.getAllStates
                (
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );

        Assert.assertEquals(expectedStates, foundCountries);
    }

    @Test
    void shouldUpdateCountryAndReturnStateDtoWhenProvidedValidId() {
        Long validId = 1L;
        StateDto expectedUpdatedState = new StateDto(validId, "Bulgaria");
        Mockito.when(stateService.updateStateById(expectedUpdatedState,validId)).thenReturn(expectedUpdatedState);
        StateDto updatedCountry = stateService.updateStateById(expectedUpdatedState, validId);
        Assert.assertEquals(expectedUpdatedState, updatedCountry);
    }

    @Test
    void shouldNotUpdateStateWhenProvidedInValidId() {
        Long validId = 1L;
        Long invalidId = 2L;
        StateDto expectedUpdatedState = new StateDto(validId, "Bulgaria");
        Mockito.when(stateService.updateStateById(expectedUpdatedState,validId)).thenReturn(expectedUpdatedState);
        StateDto updatedCountry = stateService.updateStateById(expectedUpdatedState, invalidId);
        Assert.assertNotEquals(expectedUpdatedState, updatedCountry);
    }
}