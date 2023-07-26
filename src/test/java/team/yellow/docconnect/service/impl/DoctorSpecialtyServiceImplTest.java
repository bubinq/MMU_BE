//package team.yellow.docconnect.service.impl;
//
//import org.junit.Assert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import team.yellow.docconnect.payload.dto.DoctorSpecialtyDto;
//import team.yellow.docconnect.payload.response.DoctorSpecialtyResponse;
//import team.yellow.docconnect.service.DoctorSpecialtyService;
//import team.yellow.docconnect.utils.AppConstants;
//
//
//@RunWith(MockitoJUnitRunner.class)
//class DoctorSpecialtyServiceImplTest {
//
//    Long doctorSpecialtyId;
//    Long doctorId;
//    Long specialtyId;
//
//    @Mock
//    private DoctorSpecialtyService doctorSpecialtyService;
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        doctorSpecialtyId = 1L;
//        doctorId = 1L;
//        specialtyId = 1L;
//    }
//
//    @Test
//    void shouldCreateDoctorSpecialtyAndReturnDoctorSpecialtyDto() {
//        DoctorSpecialtyDto doctorSpecialtyToInsert = new DoctorSpecialtyDto(doctorSpecialtyId, doctorId,specialtyId);
//        Mockito.when(doctorSpecialtyService.createDoctorSpecialty(doctorSpecialtyToInsert,doctorId,specialtyId)).thenReturn(doctorSpecialtyToInsert);
//        DoctorSpecialtyDto createdDoctorSpecialty = doctorSpecialtyService.createDoctorSpecialty(doctorSpecialtyToInsert,doctorId,specialtyId);
//        Assert.assertEquals(doctorSpecialtyToInsert, createdDoctorSpecialty);
//    }
//
//    @Test
//    void shouldReturnDoctorSpecialtyDtoWhenProvidedValidId() {
//        Long validId = 1L;
//        DoctorSpecialtyDto expectedDoctorSpecialty = new DoctorSpecialtyDto(validId, doctorId,specialtyId);
//        Mockito.when(doctorSpecialtyService.getDoctorSpecialtyById(validId)).thenReturn(expectedDoctorSpecialty);
//        DoctorSpecialtyDto foundDoctorSpecialty = doctorSpecialtyService.getDoctorSpecialtyById(validId);
//        Assert.assertEquals(expectedDoctorSpecialty, foundDoctorSpecialty);
//    }
//
//    @Test
//    void shouldNotReturnExpectedDoctorSpecialtyDtoWhenProvidedInvalidId() {
//        Long validId = 1L;
//        Long invalidId = 2L;
//        DoctorSpecialtyDto expectedDoctorSpecialty = new DoctorSpecialtyDto(validId, doctorId,specialtyId);
//        Mockito.when(doctorSpecialtyService.getDoctorSpecialtyById(validId)).thenReturn(expectedDoctorSpecialty);
//        DoctorSpecialtyDto foundDoctorSpecialty = doctorSpecialtyService.getDoctorSpecialtyById(invalidId);
//        Assert.assertNotEquals(expectedDoctorSpecialty, foundDoctorSpecialty);
//    }
//
//    @Test
//    void shouldReturnAllDoctorSpecialtiesPaginated() {
//        DoctorSpecialtyResponse expectedDoctorSpecialties = new DoctorSpecialtyResponse();
//        Mockito.when(doctorSpecialtyService
//                .getAllDoctorSpecialties
//                        (
//                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
//                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
//                                AppConstants.DEFAULT_SORT_BY,
//                                AppConstants.DEFAULT_SORT_DIRECTION
//                        )
//        ).thenReturn(expectedDoctorSpecialties);
//
//        DoctorSpecialtyResponse foundDoctorSpecialties = doctorSpecialtyService.getAllDoctorSpecialties
//                (
//                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
//                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
//                        AppConstants.DEFAULT_SORT_BY,
//                        AppConstants.DEFAULT_SORT_DIRECTION
//                );
//
//        Assert.assertEquals(expectedDoctorSpecialties, foundDoctorSpecialties);
//    }
//
//    @Test
//    void shouldReturnAllDoctorSpecialtiesByDoctorPaginated() {
//        DoctorSpecialtyResponse expectedDoctorSpecialties = new DoctorSpecialtyResponse();
//        Mockito.when(doctorSpecialtyService
//                .getAllDoctorSpecialtiesByDoctorId
//                        (
//                                doctorId,
//                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
//                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
//                                AppConstants.DEFAULT_SORT_BY,
//                                AppConstants.DEFAULT_SORT_DIRECTION
//                        )
//        ).thenReturn(expectedDoctorSpecialties);
//
//        DoctorSpecialtyResponse foundDoctorSpecialties = doctorSpecialtyService.getAllDoctorSpecialtiesByDoctorId
//                (
//                        doctorId,
//                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
//                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
//                        AppConstants.DEFAULT_SORT_BY,
//                        AppConstants.DEFAULT_SORT_DIRECTION
//                );
//
//        Assert.assertEquals(expectedDoctorSpecialties, foundDoctorSpecialties);
//    }
//
//    @Test
//    void shouldReturnAllDoctorSpecialtiesBySpecialtyPaginated() {
//        DoctorSpecialtyResponse expectedDoctorSpecialties = new DoctorSpecialtyResponse();
//        Mockito.when(doctorSpecialtyService
//                .getAllDoctorSpecialtiesBySpecialtyId
//                        (
//                                specialtyId,
//                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
//                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
//                                AppConstants.DEFAULT_SORT_BY,
//                                AppConstants.DEFAULT_SORT_DIRECTION
//                        )
//        ).thenReturn(expectedDoctorSpecialties);
//
//        DoctorSpecialtyResponse foundDoctorSpecialties = doctorSpecialtyService.getAllDoctorSpecialtiesBySpecialtyId
//                (
//                        specialtyId,
//                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
//                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
//                        AppConstants.DEFAULT_SORT_BY,
//                        AppConstants.DEFAULT_SORT_DIRECTION
//                );
//
//        Assert.assertEquals(expectedDoctorSpecialties, foundDoctorSpecialties);
//    }
//
//    @Test
//    void shouldUpdateDoctorSpecialtyAndReturnDoctorSpecialtyDtoWhenProvidedValidId() {
//        Long validId = 1L;
//        DoctorSpecialtyDto expectedUpdatedDoctorSpecialty = new DoctorSpecialtyDto(validId, doctorId,specialtyId);
//        Mockito.when(doctorSpecialtyService.updateDoctorSpecialtyById(expectedUpdatedDoctorSpecialty,validId)).thenReturn(expectedUpdatedDoctorSpecialty);
//        DoctorSpecialtyDto updatedDoctorSpecialty = doctorSpecialtyService.updateDoctorSpecialtyById(expectedUpdatedDoctorSpecialty,validId);
//        Assert.assertEquals(expectedUpdatedDoctorSpecialty, updatedDoctorSpecialty);
//    }
//
//    @Test
//    void shouldNotUpdateDoctorSpecialtyWhenProvidedInValidId() {
//        Long validId = 1L;
//        Long invalidId = 2L;
//        DoctorSpecialtyDto expectedUpdatedDoctorSpecialty = new DoctorSpecialtyDto(validId, doctorId,specialtyId);
//        Mockito.when(doctorSpecialtyService.updateDoctorSpecialtyById(expectedUpdatedDoctorSpecialty, validId)).thenReturn(expectedUpdatedDoctorSpecialty);
//        DoctorSpecialtyDto updatedDoctorSpecialty = doctorSpecialtyService.updateDoctorSpecialtyById(expectedUpdatedDoctorSpecialty, invalidId);
//        Assert.assertNotEquals(expectedUpdatedDoctorSpecialty, updatedDoctorSpecialty);
//    }
//}