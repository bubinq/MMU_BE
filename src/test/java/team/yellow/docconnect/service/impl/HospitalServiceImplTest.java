package team.yellow.docconnect.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import team.yellow.docconnect.payload.dto.HospitalDto;
import team.yellow.docconnect.payload.response.HospitalResponse;
import team.yellow.docconnect.service.HospitalService;
import team.yellow.docconnect.utils.AppConstants;


@RunWith(MockitoJUnitRunner.class)
class HospitalServiceImplTest {


    @Mock
    private HospitalService hospitalService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateHospitalAndReturnHospitalDto() {
        Long hospitalId = 1L;
        Long cityId = 1L;
        HospitalDto hospital = new HospitalDto(hospitalId, "Hospital", "address", cityId);
        Mockito.when(hospitalService.createHospital(hospital)).thenReturn(hospital);
        HospitalDto newHospital = hospitalService.createHospital(hospital);
        Assert.assertEquals(hospital, newHospital);
    }

    @Test
    void testReturnHospitalDtoWhenProvidedValidId() {
        Long hospitalId = 1L;
        Long cityId = 1L;
        HospitalDto hospital = new HospitalDto(hospitalId, "Hospital", "address", cityId);
        Mockito.when(hospitalService.getHospitalById(hospitalId)).thenReturn(hospital);
        HospitalDto newHospital = hospitalService.getHospitalById(hospitalId);
        Assert.assertEquals(hospital, newHospital);
    }

    @Test
    void testNotReturnExpectedHospitalDtoWhenProvidedInvalidId() {
        Long validHospitalId = 1L;
        Long invalidHospitalId = 2L;
        Long cityId = 1L;
        HospitalDto hospital = new HospitalDto(validHospitalId, "Hospital", "address", cityId);
        Mockito.when(hospitalService.getHospitalById(validHospitalId)).thenReturn(hospital);
        HospitalDto newHospital = hospitalService.getHospitalById(invalidHospitalId);
        Assert.assertNotEquals(hospital, newHospital);
    }

    @Test
    void testReturnAllHospitalsPaginated() {
        HospitalResponse expectedHospitalResponse = new HospitalResponse();
        Mockito.when(hospitalService. getAllHospitals(

                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                                AppConstants.DEFAULT_SORT_BY,
                                AppConstants.DEFAULT_SORT_DIRECTION
                        )
        ).thenReturn(expectedHospitalResponse);

        HospitalResponse foundHospitalResponse = hospitalService.getAllHospitals
                (
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );

        Assert.assertEquals(expectedHospitalResponse, foundHospitalResponse);
    }

    @Test
    void testUpdateHospitalAndReturnHospitalDtoWhenProvidedValidId() {
        Long hospitalId = 1L;
        Long cityId = 1L;
        HospitalDto hospital = new HospitalDto(hospitalId, "Hospital", "address", cityId);
        Mockito.when(hospitalService.updateHospitalById(hospitalId,hospital)).thenReturn(hospital);
        HospitalDto newHospital = hospitalService.updateHospitalById(hospitalId, hospital);
        Assert.assertEquals(hospital, newHospital);
    }

    @Test
    void testNotUpdateHospitalWhenProvidedInValidId() {
        Long validHospitalId = 1L;
        Long invalidHospitalId = 2L;
        Long cityId = 1L;
        HospitalDto hospital = new HospitalDto(validHospitalId, "Hospital", "address", cityId);
        Mockito.when(hospitalService.updateHospitalById(validHospitalId,hospital)).thenReturn(hospital);
        HospitalDto newHospital = hospitalService.updateHospitalById(invalidHospitalId, hospital);
        Assert.assertNotEquals(hospital, newHospital);
    }
}