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
import team.yellow.docconnect.payload.dto.DoctorSpecialtyDto;
import team.yellow.docconnect.payload.response.DoctorSpecialtyResponse;
import team.yellow.docconnect.service.DoctorSpecialtyService;
import team.yellow.docconnect.utils.AppConstants;

@RunWith(MockitoJUnitRunner.class)
class DoctorSpecialtyControllerTest {

    Long doctorSpecialtyId;
    Long doctorId;
    Long specialtyId;

    @InjectMocks
    private DoctorSpecialtyController doctorSpecialtyController;

    @Mock
    private DoctorSpecialtyService doctorSpecialtyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        doctorSpecialtyId = 1L;
        doctorId = 1L;
        specialtyId = 1L;
    }

    @Test
    void shouldCreateDoctorSpecialtyAndReturnCreatedResponse() {

        DoctorSpecialtyDto doctorSpecialtyToInsert =new DoctorSpecialtyDto(doctorSpecialtyId, doctorId, specialtyId );
        Mockito.when(doctorSpecialtyService.createDoctorSpecialty(doctorSpecialtyToInsert, doctorId, specialtyId)).thenReturn(doctorSpecialtyToInsert);
        ResponseEntity<DoctorSpecialtyDto> receivedResponse = doctorSpecialtyController.createDoctorSpecialty(doctorSpecialtyToInsert,doctorId,specialtyId);
        Assert.assertEquals(HttpStatus.CREATED, receivedResponse.getStatusCode());
        Assert.assertEquals(doctorSpecialtyToInsert, receivedResponse.getBody());
    }

    @Test
    void shouldReturnOKResponseAndProperDoctorSpecialtyWhenProvidedValidId() {
        Long validDoctorSpecialtyId = 1L;
        DoctorSpecialtyDto expectedDoctorSpecialty = new DoctorSpecialtyDto(validDoctorSpecialtyId, doctorId,specialtyId);
        Mockito.when(doctorSpecialtyService.getDoctorSpecialtyById(validDoctorSpecialtyId)).thenReturn(expectedDoctorSpecialty);
        ResponseEntity<DoctorSpecialtyDto> receivedResponse = doctorSpecialtyController.getDoctorSpecialtyById(validDoctorSpecialtyId);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedDoctorSpecialty, receivedResponse.getBody());
    }

    @Test
    void shouldNotReturnProperDoctorSpecialtyWhenProvidedInvalidId() {
        Long validDoctorSpecialtyId = 1L;
        Long invalidDoctorSpecialtyId = 2L;
        DoctorSpecialtyDto expectedDoctorSpecialty = new DoctorSpecialtyDto(validDoctorSpecialtyId, doctorId,specialtyId);
        Mockito.when(doctorSpecialtyService.getDoctorSpecialtyById(validDoctorSpecialtyId)).thenReturn(expectedDoctorSpecialty);
        ResponseEntity<DoctorSpecialtyDto> receivedResponse = doctorSpecialtyController.getDoctorSpecialtyById(invalidDoctorSpecialtyId);
        Assert.assertNotEquals(expectedDoctorSpecialty, receivedResponse.getBody());
    }

    @Test
    void shouldReturnOKResponseAndAllDoctorSpecialtiesPaginated() {
        DoctorSpecialtyResponse expectedDoctorSpecialties = new DoctorSpecialtyResponse();

        Mockito.when(doctorSpecialtyService
                .getAllDoctorSpecialties
                        (
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                                AppConstants.DEFAULT_SORT_BY,
                                AppConstants.DEFAULT_SORT_DIRECTION
                        )
        ).thenReturn(expectedDoctorSpecialties);

        ResponseEntity<DoctorSpecialtyResponse> receivedResponse = doctorSpecialtyController.getAllDoctorsSpecialties
                (
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedDoctorSpecialties, receivedResponse.getBody());
    }
    @Test
    void shouldReturnOKResponseAndAllDoctorSpecialtiesByDoctorPaginated() {
        DoctorSpecialtyResponse expectedDoctorSpecialties = new DoctorSpecialtyResponse();

        Mockito.when(doctorSpecialtyService
                .getAllDoctorSpecialtiesByDoctorId
                        (
                                doctorId,
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                                AppConstants.DEFAULT_SORT_BY,
                                AppConstants.DEFAULT_SORT_DIRECTION
                        )
        ).thenReturn(expectedDoctorSpecialties);

        ResponseEntity<DoctorSpecialtyResponse> receivedResponse = doctorSpecialtyController.getAllDoctorsSpecialtiesByDoctorId
                (
                        doctorId,
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedDoctorSpecialties, receivedResponse.getBody());
    }
    @Test
    void shouldReturnOKResponseAndAllDoctorSpecialtiesBySpecialtyPaginated() {
        DoctorSpecialtyResponse expectedDoctorSpecialties = new DoctorSpecialtyResponse();

        Mockito.when(doctorSpecialtyService
                .getAllDoctorSpecialtiesBySpecialtyId
                        (
                                specialtyId,
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                                AppConstants.DEFAULT_SORT_BY,
                                AppConstants.DEFAULT_SORT_DIRECTION
                        )
        ).thenReturn(expectedDoctorSpecialties);

        ResponseEntity<DoctorSpecialtyResponse> receivedResponse = doctorSpecialtyController.getAllDoctorsSpecialtiesBySpecialtyId
                (
                        specialtyId,
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedDoctorSpecialties, receivedResponse.getBody());
    }

    @Test
    void shouldUpdateDoctorSpecialtyAndReturnOKResponseWhenProvidedValidId() {
        Long validId = 1L;
        DoctorSpecialtyDto expectedUpdatedDoctorSpecialty = new DoctorSpecialtyDto(validId, doctorId,specialtyId);
        Mockito.when(doctorSpecialtyService.updateDoctorSpecialtyById(expectedUpdatedDoctorSpecialty,validId)).thenReturn(expectedUpdatedDoctorSpecialty);
        ResponseEntity<DoctorSpecialtyDto> receivedResponse = doctorSpecialtyController.updateDoctorSpecialtyById(validId,expectedUpdatedDoctorSpecialty);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedUpdatedDoctorSpecialty, receivedResponse.getBody());
    }

    @Test
    void shouldNotUpdateDoctorSpecialtyWhenProvidedInvalidId() {
        Long validId = 1L;
        Long invalidId = 2L;
        DoctorSpecialtyDto expectedUpdatedDoctorSpecialty = new DoctorSpecialtyDto(validId, doctorId,specialtyId);
        Mockito.when(doctorSpecialtyService.updateDoctorSpecialtyById(expectedUpdatedDoctorSpecialty, validId )).thenReturn(expectedUpdatedDoctorSpecialty);
        ResponseEntity<DoctorSpecialtyDto> receivedResponse = doctorSpecialtyController.updateDoctorSpecialtyById(invalidId,expectedUpdatedDoctorSpecialty);
        Assert.assertNotEquals(expectedUpdatedDoctorSpecialty, receivedResponse.getBody());
    }

    @Test
    void shouldDeleteDoctorSpecialtyAndReturnOKResponse() {
        Long doctorSpecialtyId = 1L;
        DoctorSpecialtyDto doctorSpecialtyToInsert = new DoctorSpecialtyDto(doctorSpecialtyId, doctorId,specialtyId);
        doctorSpecialtyService.createDoctorSpecialty(doctorSpecialtyToInsert,doctorId,specialtyId);
        ResponseEntity<String> receivedResponse = doctorSpecialtyController.deleteDoctorSpecialtyById(doctorSpecialtyId);
        Assertions.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assertions.assertEquals(AppConstants.SUCCESSFULLY_DELETED_MESSAGE, receivedResponse.getBody());
    }
}