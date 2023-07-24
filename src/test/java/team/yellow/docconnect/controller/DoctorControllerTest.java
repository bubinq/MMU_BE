package team.yellow.docconnect.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import team.yellow.docconnect.service.DoctorService;

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
    void shouldCreateDoctorAndReturnCreatedResponseWhenProvidedValidCityId() {
        Long validCityId = 1L;
    }

    @Test
    void getDoctorById() {
    }

    @Test
    void getAllDoctors() {
    }

    @Test
    void getAllDoctorsByCity() {
    }

    @Test
    void updateDoctorById() {
    }

    @Test
    void deleteDoctorById() {
    }
}