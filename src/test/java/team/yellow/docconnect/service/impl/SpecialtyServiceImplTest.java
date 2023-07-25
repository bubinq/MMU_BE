package team.yellow.docconnect.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import team.yellow.docconnect.payload.dto.SpecialtyDto;
import team.yellow.docconnect.payload.response.SpecialtyResponse;
import team.yellow.docconnect.service.SpecialtyService;
import team.yellow.docconnect.utils.AppConstants;


@RunWith(MockitoJUnitRunner.class)
class SpecialtyServiceImplTest {


    @Mock
    private SpecialtyService specialtyService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateSpecialtyAndReturnSpecialtyDto() {
        Long specialtyId = 1L;
        SpecialtyDto specialtyToInsert = new SpecialtyDto(specialtyId, "specialty test","https://www.example.com/image.png");
        Mockito.when(specialtyService.createSpecialty(specialtyToInsert)).thenReturn(specialtyToInsert);
        SpecialtyDto createdSpecialty = specialtyService.createSpecialty(specialtyToInsert);
        Assert.assertEquals(specialtyToInsert, createdSpecialty);
    }

    @Test
    void shouldReturnSpecialtyDtoWhenProvidedValidId() {
        Long validId = 1L;
        SpecialtyDto expectedSpecialty = new SpecialtyDto(validId, "specialty test","https://www.example.com/image.png");
        Mockito.when(specialtyService.getSpecialtyById(validId)).thenReturn(expectedSpecialty);
        SpecialtyDto foundSpecialty = specialtyService.getSpecialtyById(validId);
        Assert.assertEquals(expectedSpecialty, foundSpecialty);
    }

    @Test
    void shouldNotReturnExpectedSpecialtyDtoWhenProvidedInvalidId() {
        Long validId = 1L;
        Long invalidId = 2L;
        SpecialtyDto expectedSpecialty = new SpecialtyDto(validId, "specialty test","https://www.example.com/image.png");
        Mockito.when(specialtyService.getSpecialtyById(validId)).thenReturn(expectedSpecialty);
        SpecialtyDto foundSpecialty = specialtyService.getSpecialtyById(invalidId);
        Assert.assertNotEquals(expectedSpecialty, foundSpecialty);
    }

    @Test
    void shouldReturnAllSpecialtiesPaginated() {
        SpecialtyResponse expectedSpecialties = new SpecialtyResponse();
        Mockito.when(specialtyService
                .getAllSpecialties
                        (
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                                Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                                AppConstants.DEFAULT_SORT_BY,
                                AppConstants.DEFAULT_SORT_DIRECTION
                        )
        ).thenReturn(expectedSpecialties);

        SpecialtyResponse foundSpecialties = specialtyService.getAllSpecialties
                (
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER),
                        Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE),
                        AppConstants.DEFAULT_SORT_BY,
                        AppConstants.DEFAULT_SORT_DIRECTION
                );

        Assert.assertEquals(expectedSpecialties, foundSpecialties);
    }

    @Test
    void shouldUpdateSpecialtyAndReturnSpecialtyDtoWhenProvidedValidId() {
        Long validId = 1L;
        SpecialtyDto expectedUpdatedSpecialty = new SpecialtyDto(validId, "specialty test","https://www.example.com/image.png");
        Mockito.when(specialtyService.updateSpecialtyById(validId,expectedUpdatedSpecialty)).thenReturn(expectedUpdatedSpecialty);
        SpecialtyDto updatedSpecialty = specialtyService.updateSpecialtyById(validId, expectedUpdatedSpecialty);
        Assert.assertEquals(expectedUpdatedSpecialty, updatedSpecialty);
    }

    @Test
    void shouldNotUpdateSpecialtyWhenProvidedInValidId() {
        Long validId = 1L;
        Long invalidId = 2L;
        SpecialtyDto expectedUpdatedSpecialty = new SpecialtyDto(validId, "specialty test","https://www.example.com/image.png");
        Mockito.when(specialtyService.updateSpecialtyById(validId, expectedUpdatedSpecialty)).thenReturn(expectedUpdatedSpecialty);
        SpecialtyDto updatedSpecialty = specialtyService.updateSpecialtyById(invalidId, expectedUpdatedSpecialty);
        Assert.assertNotEquals(expectedUpdatedSpecialty, updatedSpecialty);
    }
}