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
    void shouldCreateStateAndReturnStateDto() {
        Long stateId = 1L;
        StateDto stateToInsert = new StateDto(stateId, "Bulgaria");
        Mockito.when(stateService.createState(stateToInsert)).thenReturn(stateToInsert);
        StateDto createdState = stateService.createState(stateToInsert);
        Assert.assertEquals(stateToInsert, createdState);
    }

    @Test
    void shouldReturnStateDtoWhenProvidedValidId() {
        Long validId = 1L;
        StateDto expectedState = new StateDto(validId, "Bulgaria");
        Mockito.when(stateService.getStateById(validId)).thenReturn(expectedState);
        StateDto foundState = stateService.getStateById(validId);
        Assert.assertEquals(expectedState, foundState);
    }

    @Test
    void shouldNotReturnExpectedStateDtoWhenProvidedInvalidId() {
        Long validId = 1L;
        Long invalidId = 2L;
        StateDto expectedState = new StateDto(validId, "Bulgaria");
        Mockito.when(stateService.getStateById(validId)).thenReturn(expectedState);
        StateDto foundState = stateService.getStateById(invalidId);
        Assert.assertNotEquals(expectedState, foundState);
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

        StateResponse foundStates = stateService.getAllStates
                (
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );

        Assert.assertEquals(expectedStates, foundStates);
    }

    @Test
    void shouldUpdateStateAndReturnStateDtoWhenProvidedValidId() {
        Long validId = 1L;
        StateDto expectedUpdatedState = new StateDto(validId, "Bulgaria");
        Mockito.when(stateService.updateStateById(expectedUpdatedState,validId)).thenReturn(expectedUpdatedState);
        StateDto updatedState = stateService.updateStateById(expectedUpdatedState, validId);
        Assert.assertEquals(expectedUpdatedState, updatedState);
    }

    @Test
    void shouldNotUpdateStateWhenProvidedInValidId() {
        Long validId = 1L;
        Long invalidId = 2L;
        StateDto expectedUpdatedState = new StateDto(validId, "Bulgaria");
        Mockito.when(stateService.updateStateById(expectedUpdatedState,validId)).thenReturn(expectedUpdatedState);
        StateDto updatedState = stateService.updateStateById(expectedUpdatedState, invalidId);
        Assert.assertNotEquals(expectedUpdatedState, updatedState);
    }
}