package team.yellow.docconnect.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import team.yellow.docconnect.payload.dto.DoctorDto;
import team.yellow.docconnect.payload.response.DoctorResponse;
import team.yellow.docconnect.service.DoctorService;
import team.yellow.docconnect.utils.AppConstants;
@RunWith(MockitoJUnitRunner.class)
class DoctorServiceImplTest {

    @Mock
    private DoctorService doctorService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateDoctorWhenProvidedValidCityId() {
        Long validCityId = 1L;
        DoctorDto doctorToInsert = new DoctorDto
                (
                        1L, "Ferdinand", "II",
                        "example@gmail.com", "yup",1,"MM UNIVERSITY BULGARIA",
                        2.5, validCityId

                );
        Mockito.when(doctorService.createDoctor(doctorToInsert, validCityId)).thenReturn(doctorToInsert);
        DoctorDto createdDoctor = doctorService.createDoctor(doctorToInsert, validCityId);
        Assert.assertEquals(doctorToInsert,createdDoctor);
    }

    @Test
    void shouldNotCreateDoctorWhenProvidedInvalidCityId() {
        Long validCityId = 1L;
        Long invalidCityId = 2L;
        DoctorDto doctorToInsert = new DoctorDto
                (
                        1L, "Ferdinand", "II",
                        "example@gmail.com", "yup",1,"MM UNIVERSITY BULGARIA",
                        2.5, validCityId

                );
        Mockito.when(doctorService.createDoctor(doctorToInsert, validCityId)).thenReturn(doctorToInsert);
        DoctorDto createdDoctor = doctorService.createDoctor(doctorToInsert, invalidCityId);
        Assert.assertNotEquals(doctorToInsert,createdDoctor);
    }



    @Test
    void shouldReturnDtoWhenProvidedValidId() {
        Long validDoctorId = 1L;
        DoctorDto expectedDoctor = new DoctorDto
                (
                        validDoctorId, "Ferdinand", "II",
                        "example@gmail.com", "yup",1,"MM UNIVERSITY BULGARIA",
                        2.5, 1L

                );
        Mockito.when(doctorService.getDoctorById(validDoctorId)).thenReturn(expectedDoctor);
        DoctorDto receivedDoctor = doctorService.getDoctorById(validDoctorId);
        Assert.assertEquals(expectedDoctor, receivedDoctor);
    }

    @Test
    void shouldNotReturnDtoAWhenProvidedInvalidId() {
        Long validDoctorId = 1L;
        Long invalidDoctorId = 2L;
        DoctorDto expectedDoctor = new DoctorDto
                (
                        validDoctorId, "Ferdinand", "II",
                        "example@gmail.com", "yup",1,"MM UNIVERSITY BULGARIA",
                        2.5, 1L

                );
        Mockito.when(doctorService.getDoctorById(validDoctorId)).thenReturn(expectedDoctor);
        DoctorDto receivedDoctor = doctorService.getDoctorById(invalidDoctorId);
        Assert.assertNotEquals(expectedDoctor, receivedDoctor);
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

        DoctorResponse receivedDoctors = doctorService.getAllDoctors
                (
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );

        Assert.assertEquals(expectedDoctors, receivedDoctors);
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

        DoctorResponse receivedDoctors = doctorService.getAllDoctorsByCityId
                (
                        validCityId,
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );

        Assert.assertEquals(expectedDoctors, receivedDoctors);

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

        DoctorResponse receivedDoctors = doctorService.getAllDoctorsByCityId
                (
                        invalidCityId,
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );

        Assert.assertNotEquals(expectedDoctors, receivedDoctors);
    }

    @Test
    void shouldUpdateDtoWhenProvidedValidId() {
        Long validDoctorId = 1L;
        DoctorDto updatedDoctor = new DoctorDto
                (
                        validDoctorId, "Ferdinand", "II",
                        "example@gmail.com", "yup",1,"MM UNIVERSITY BULGARIA",
                        2.5, 1L

                );
        Mockito.when(doctorService.updateDoctorById(validDoctorId, updatedDoctor)).thenReturn(updatedDoctor);
        DoctorDto receivedDto = doctorService.updateDoctorById(validDoctorId, updatedDoctor);
        Assert.assertEquals(updatedDoctor, receivedDto);
    }

    @Test
    void shouldNotUpdateDtoWhenProvidedInvalidId() {
        Long validDoctorId = 1L;
        Long invalidDoctorId= 2L;
        DoctorDto updatedDoctor = new DoctorDto
                (
                        validDoctorId, "Ferdinand", "II",
                        "example@gmail.com", "yup",1,"MM UNIVERSITY BULGARIA",
                        2.5, 1L

                );
        Mockito.when(doctorService.updateDoctorById(validDoctorId, updatedDoctor)).thenReturn(updatedDoctor);
        DoctorDto receivedDto = doctorService.updateDoctorById(invalidDoctorId, updatedDoctor);

        Assert.assertNotEquals(updatedDoctor,receivedDto);
    }
}