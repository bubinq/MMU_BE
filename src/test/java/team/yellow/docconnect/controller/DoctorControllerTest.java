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
import team.yellow.docconnect.payload.dto.DoctorDto;
import team.yellow.docconnect.payload.response.DoctorResponse;
import team.yellow.docconnect.service.DoctorService;
import team.yellow.docconnect.utils.AppConstants;

@RunWith(MockitoJUnitRunner.class)
class DoctorControllerTest {

    @InjectMocks
    private DoctorController doctorController;
    @Mock
    private DoctorService doctorService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateDoctorAndReturnCreatedResponseWhenProvidedValidIds() {
        Long validCityId = 1L;
        Long validSpecialtyId =  1L;
        Long validCountryId = 1L;
        DoctorDto doctorToInsert = new DoctorDto
                (
                            1L, "Ferdinand", "II",
                        "example@gmail.com", "yup",1,"MM UNIVERSITY BULGARIA",
                        2.5, "http://www.example.com/","Great Saint Hospital of Europe",
                        validCountryId, validSpecialtyId, validCityId

                );
        Mockito.when(doctorService.createDoctor(doctorToInsert, validCityId, validCountryId, validSpecialtyId))
                .thenReturn(doctorToInsert);
        ResponseEntity<DoctorDto> receivedResponse = doctorController.createDoctor(doctorToInsert, validCityId,
                validCountryId, validSpecialtyId);
        Assert.assertEquals(HttpStatus.CREATED, receivedResponse.getStatusCode());
        Assert.assertEquals(doctorToInsert, receivedResponse.getBody());
    }

    @Test
    void shouldNotCreateDoctorWhenProvidedInvalidCityId() {
        Long validCityId = 1L;
        Long validSpecialtyId =  1L;
        Long validCountryId = 1L;
        Long invalidCityId = 2L;
        DoctorDto doctorToInsert = new DoctorDto
                (
                        1L, "Ferdinand", "II",
                        "example@gmail.com", "yup",1,"MM UNIVERSITY BULGARIA",
                        2.5, "http://www.example.com/","Great Saint Hospital of Europe",
                        validCountryId, validSpecialtyId, validCityId

                );
        Mockito.when(doctorService.createDoctor(doctorToInsert, validCityId, validCountryId, validSpecialtyId))
                .thenReturn(doctorToInsert);
        ResponseEntity<DoctorDto> receivedResponse = doctorController.createDoctor(doctorToInsert, invalidCityId,
                validCountryId , validSpecialtyId);
        Assert.assertNotEquals(doctorToInsert, receivedResponse.getBody());
    }



    @Test
    void shouldReturnDtoAndOKResponseWhenProvidedValidId() {
        Long validDoctorId = 1L;
        DoctorDto foundDoctor = new DoctorDto
                (
                        validDoctorId, "Ferdinand", "II",
                        "example@gmail.com", "yup",1,"MM UNIVERSITY BULGARIA",
                        2.5, "http://www.example.com/","Great Saint Hospital of Europe",
                        1L, 1L, 1L

                );
        Mockito.when(doctorService.getDoctorById(validDoctorId)).thenReturn(foundDoctor);
        ResponseEntity<DoctorDto> receivedResponse = doctorController.getDoctorById(validDoctorId);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(foundDoctor, receivedResponse.getBody());
    }

    @Test
    void shouldNotReturnDtoWhenProvidedInvalidId() {
        Long validDoctorId = 1L;
        Long invalidDoctorId = 2L;
        DoctorDto foundDoctor = new DoctorDto
                (
                        validDoctorId, "Ferdinand", "II",
                        "example@gmail.com", "yup",1,"MM UNIVERSITY BULGARIA",
                        2.5, "http://www.example.com/","Great Saint Hospital of Europe",
                        1L, 1L, 1L

                );
        Mockito.when(doctorService.getDoctorById(validDoctorId)).thenReturn(foundDoctor);
        ResponseEntity<DoctorDto> receivedResponse = doctorController.getDoctorById(invalidDoctorId);
        Assert.assertNotEquals(foundDoctor, receivedResponse.getBody());
    }

    @Test
    void shouldReturnAllDoctorsPaginated() {
        DoctorResponse expectedDoctors = new DoctorResponse();
        Mockito.when(doctorService
                .getAllDoctors
                        (
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                                AppConstants.DEFAULT_SORT_BY,
                                AppConstants.DEFAULT_SORT_DIRECTION
                        )
        ).thenReturn(expectedDoctors);

        ResponseEntity<DoctorResponse> receivedResponse = doctorController.getAllDoctors
                (
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                 );
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedDoctors, receivedResponse.getBody());
    }

    @Test
    void shouldReturnAllDoctorsInCityWhenProvidedValidCityId() {
        Long validCityId = 1L;
        DoctorResponse expectedDoctors = new DoctorResponse();
        Mockito.when(doctorService
                .getAllDoctorsByCityId
                        (
                                validCityId,
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                                AppConstants.DEFAULT_SORT_BY,
                                AppConstants.DEFAULT_SORT_DIRECTION
                        )
        ).thenReturn(expectedDoctors);

        ResponseEntity<DoctorResponse> receivedResponse = doctorController.getAllDoctorsByCity
                (
                        validCityId,
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(expectedDoctors, receivedResponse.getBody());

    }

    @Test
    void shouldNotReturnAllDoctorsInCityWhenProvidedInvalidCityId() {
        Long validCityId = 1L;
        Long invalidCityId = 2L;
        DoctorResponse expectedDoctors = new DoctorResponse();
        Mockito.when(doctorService
                .getAllDoctorsByCityId
                        (
                                validCityId,
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                                AppConstants.DEFAULT_SORT_BY,
                                AppConstants.DEFAULT_SORT_DIRECTION
                        )
        ).thenReturn(expectedDoctors);

        ResponseEntity<DoctorResponse> receivedResponse = doctorController.getAllDoctorsByCity
                (
                        invalidCityId,
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );

        Assert.assertNotEquals(expectedDoctors, receivedResponse.getBody());
    }

    @Test
    void shouldUpdateDtoAndReturnOKWhenProvidedValidId() {
        Long validDoctorId = 1L;
        DoctorDto updatedDoctor = new DoctorDto
                (
                        validDoctorId, "Ferdinand", "II",
                        "example@gmail.com", "yup",1,"MM UNIVERSITY BULGARIA",
                        2.5, "http://www.example.com/","Great Saint Hospital of Europe",
                        1L, 1L, 1L

                );
        Mockito.when(doctorService.updateDoctorById(validDoctorId, updatedDoctor)).thenReturn(updatedDoctor);
        ResponseEntity<DoctorDto> receivedResponse = doctorController.updateDoctorById(validDoctorId, updatedDoctor);
        Assert.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assert.assertEquals(updatedDoctor, receivedResponse.getBody());
    }

    @Test
    void shouldNotUpdateDtoWhenProvidedInvalidId() {
        Long validDoctorId = 1L;
        Long invalidDoctorId= 2L;
        DoctorDto updatedDoctor = new DoctorDto
                (
                        validDoctorId, "Ferdinand", "II",
                        "example@gmail.com", "yup",1,"MM UNIVERSITY BULGARIA",
                        2.5, "http://www.example.com/","Great Saint Hospital of Europe",
                        1L, 1L, 1L

                );
        Mockito.when(doctorService.updateDoctorById(validDoctorId, updatedDoctor)).thenReturn(updatedDoctor);
        ResponseEntity<DoctorDto> receivedResponse = doctorController.updateDoctorById(invalidDoctorId, updatedDoctor);

        Assert.assertNotEquals(updatedDoctor, receivedResponse.getBody());
    }

    @Test
    void shouldDeleteDoctorAndReturnOKResponse() {
        Long doctorId = 1L;
        DoctorDto doctorToDelete = new DoctorDto
                (
                        doctorId, "Ferdinand", "II",
                        "example@gmail.com", "yup",1,"MM UNIVERSITY BULGARIA",
                        2.5, "http://www.example.com/","Great Saint Hospital of Europe",
                        1L, 1L, 1L

                );
        doctorService.createDoctor(doctorToDelete, 1L, 1L, 1L);
        ResponseEntity<String> receivedResponse = doctorController.deleteDoctorById(doctorId);
        Assertions.assertEquals(HttpStatus.OK, receivedResponse.getStatusCode());
        Assertions.assertEquals("Successfully deleted Doctor with id:"+ doctorId, receivedResponse.getBody());

    }
}