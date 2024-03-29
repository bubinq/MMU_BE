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
import team.yellow.docconnect.payload.dto.HospitalDto;
import team.yellow.docconnect.payload.response.HospitalResponse;
import team.yellow.docconnect.service.HospitalService;
import team.yellow.docconnect.utils.AppConstants;
import team.yellow.docconnect.utils.Messages;

@RunWith(MockitoJUnitRunner.class)
class HospitalControllerTest {

    @InjectMocks
    private HospitalController hospitalController;

    @Mock
    private HospitalService hospitalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateHospitalAndReturnCreatedResponse() {
        Long hospitalId = 1L;
        Long cityId = 1L;
        Long stateId = 1L;
        HospitalDto hospital = new HospitalDto(hospitalId, "Hospital", "address", cityId, stateId);
        Mockito.when(hospitalService.createHospital(hospital, cityId, stateId)).thenReturn(hospital);
        ResponseEntity<HospitalDto> hospitalResponse = hospitalController.createHospital(hospital, cityId, stateId);
        Assert.assertEquals(HttpStatus.CREATED, hospitalResponse.getStatusCode());
        Assert.assertEquals(hospital, hospitalResponse.getBody());
    }

    @Test
    void testReturnOKResponseAndProperHospitalWhenProvidedValidId() {
        Long hospitalId = 1L;
        Long cityId = 1L;
        Long stateId = 1L;
        HospitalDto hospital = new HospitalDto(hospitalId, "Hospital", "address", cityId, stateId);
        Mockito.when(hospitalService.getHospitalById(hospitalId)).thenReturn(hospital);
        ResponseEntity<HospitalDto> hospitalResponse = hospitalController.getHospitalById(hospitalId);
        Assert.assertEquals(HttpStatus.OK, hospitalResponse.getStatusCode());
        Assert.assertEquals(hospital, hospitalResponse.getBody());
    }

    @Test
    void testNotReturnProperHospitalWhenProvidedInvalidId() {
        Long validHospitalId = 1L;
        Long invalidHospitalId = 2L;
        Long cityId = 1L;
        Long stateId = 1L;
        HospitalDto hospital = new HospitalDto(validHospitalId, "Hospital", "address", cityId, stateId);
        Mockito.when(hospitalService.getHospitalById(validHospitalId)).thenReturn(hospital);
        ResponseEntity<HospitalDto> hospitalResponse = hospitalController.getHospitalById(invalidHospitalId);
        Assert.assertNotEquals(hospital, hospitalResponse.getBody());
    }

    @Test
    void testReturnOKResponseAndAllHospitalsByCityIdPaginated() {
        Long cityId = 1L;
        HospitalResponse expectedHospitalResponse = new HospitalResponse();

        Mockito.when(hospitalService
                .getAllHospitalsByCityId
                        (
                                cityId,
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                                AppConstants.DEFAULT_SORT_BY,
                                AppConstants.DEFAULT_SORT_DIRECTION
                        )
        ).thenReturn(expectedHospitalResponse);

        ResponseEntity<HospitalResponse> receivedHospitalResponse = hospitalController.getAllHospitalsByCity
                (
                        cityId,
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );
        Assert.assertEquals(HttpStatus.OK, receivedHospitalResponse.getStatusCode());
        Assert.assertEquals(expectedHospitalResponse, receivedHospitalResponse.getBody());
    }

    @Test
    void testReturnOKResponseAndAllHospitalsByStateIdPaginated() {
        Long stateId = 1L;
        HospitalResponse expectedHospitalResponse = new HospitalResponse();

        Mockito.when(hospitalService
                .getAllHospitalsByStateId
                        (
                                stateId,
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                                AppConstants.DEFAULT_SORT_BY,
                                AppConstants.DEFAULT_SORT_DIRECTION
                        )
        ).thenReturn(expectedHospitalResponse);

        ResponseEntity<HospitalResponse> receivedHospitalResponse = hospitalController.getAllHospitalsByState
                (
                        stateId,
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );
        Assert.assertEquals(HttpStatus.OK, receivedHospitalResponse.getStatusCode());
        Assert.assertEquals(expectedHospitalResponse, receivedHospitalResponse.getBody());
    }

    @Test
    void testUpdateHospitalAndReturnOKResponseWhenProvidedValidId() {
        Long hospitalId = 1L;
        Long cityId = 1L;
        Long stateId = 1L;
        HospitalDto hospital = new HospitalDto(hospitalId, "Hospital", "address", cityId, stateId);
        Mockito.when(hospitalService.updateHospitalById(hospitalId, hospital)).thenReturn(hospital);
        ResponseEntity<HospitalDto> receivedResponse = hospitalController.updateHospitalById(hospital, hospitalId);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(hospital, receivedResponse.getBody());
    }

    @Test
    void testNotUpdateHospitalWhenProvidedInvalidId() {
        Long validHospitalId = 1L;
        Long invalidHospitalId = 2L;
        Long cityId = 1L;
        Long stateId = 1L;
        HospitalDto hospital = new HospitalDto(validHospitalId, "Hospital", "address", cityId, stateId);
        Mockito.when(hospitalService.updateHospitalById(validHospitalId, hospital)).thenReturn(hospital);
        ResponseEntity<HospitalDto> receivedResponse = hospitalController.updateHospitalById(hospital, invalidHospitalId);
        Assert.assertNotEquals(hospital, receivedResponse.getBody());
    }

    @Test
    void testDeleteHospitalAndReturnOKResponse() {
        Long hospitalId = 1L;
        Long cityId = 1L;
        Long stateId = 1L;
        HospitalDto hospital = new HospitalDto(hospitalId, "Hospital", "address", cityId, stateId);
        hospitalService.createHospital(hospital, cityId, stateId);
        ResponseEntity<String> receivedResponse = hospitalController.deleteHospitalById(hospitalId);
        Assertions.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assertions.assertEquals(Messages.SUCCESSFULLY_DELETED_MESSAGE, receivedResponse.getBody());
    }
}