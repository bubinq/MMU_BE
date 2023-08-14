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
import team.yellow.docconnect.payload.dto.StateDto;
import team.yellow.docconnect.payload.response.StateResponse;
import team.yellow.docconnect.service.StateService;
import team.yellow.docconnect.utils.AppConstants;

@RunWith(MockitoJUnitRunner.class)
class StateControllerTest {

    @InjectMocks
    private StateController stateController;

    @Mock
    private StateService stateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateStateAndReturnCreatedResponse() {
        Long stateId = 1L;
        StateDto stateToInsert = new StateDto(stateId, "Bulgaria");
        Mockito.when(stateService.createState(stateToInsert)).thenReturn(stateToInsert);
        ResponseEntity<StateDto> receivedResponse = stateController.createState(stateToInsert);
        Assert.assertEquals(HttpStatus.CREATED, receivedResponse.getStatusCode());
        Assert.assertEquals(stateToInsert, receivedResponse.getBody());
    }

    @Test
    void shouldReturnOKResponseAndProperStateWhenProvidedValidId() {
        Long validStateId = 1L;
        StateDto expectedState = new StateDto(validStateId, "Bulgaria");
        Mockito.when(stateService.getStateById(validStateId)).thenReturn(expectedState);
        ResponseEntity<StateDto> receivedResponse = stateController.getStateById(validStateId);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedState, receivedResponse.getBody());
    }

    @Test
    void shouldNotReturnProperStateWhenProvidedInvalidId() {
        Long validStateId = 1L;
        Long invalidStateId = 2L;
        StateDto expectedState = new StateDto(validStateId, "Bulgaria");
        Mockito.when(stateService.getStateById(validStateId)).thenReturn(expectedState);
        ResponseEntity<StateDto> receivedResponse = stateController.getStateById(invalidStateId);
        Assert.assertNotEquals(expectedState, receivedResponse.getBody());
    }

    @Test
    void shouldReturnOKResponseAndAllStatesPaginated() {
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

        ResponseEntity<StateResponse> receivedResponse = stateController.getAllStates
                (
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedStates, receivedResponse.getBody());
    }

    @Test
    void shouldUpdateStateAndReturnOKResponseWhenProvidedValidId() {
        Long validId = 1L;
        StateDto expectedUpdatedState = new StateDto(validId, "Bulgaria");
        Mockito.when(stateService.updateStateById(expectedUpdatedState,validId)).thenReturn(expectedUpdatedState);
        ResponseEntity<StateDto> receivedResponse = stateController.updateStateById(validId, expectedUpdatedState);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedUpdatedState, receivedResponse.getBody());
    }

    @Test
    void shouldNotUpdateStateWhenProvidedInvalidId() {
        Long validId = 1L;
        Long invalidId = 2L;
        StateDto expectedUpdatedState = new StateDto(validId, "Bulgaria");
        Mockito.when(stateService.updateStateById(expectedUpdatedState,validId)).thenReturn(expectedUpdatedState);
        ResponseEntity<StateDto> receivedResponse = stateController.updateStateById(invalidId, expectedUpdatedState);
        Assert.assertNotEquals(expectedUpdatedState, receivedResponse.getBody());
    }

    @Test
    void shouldDeleteStateAndReturnOKResponse() {
        Long stateId = 1L;
        StateDto stateToInsert = new StateDto(stateId, "Bulgaria");
        stateService.createState(stateToInsert);
        ResponseEntity<String> receivedResponse = stateController.deleteStateById(stateId);
        Assertions.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assertions.assertEquals("Successfully deleted State with id: "+ stateId, receivedResponse.getBody());
    }
}