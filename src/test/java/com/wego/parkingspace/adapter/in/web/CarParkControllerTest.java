package com.wego.parkingspace.adapter.in.web;

import com.wego.parkingspace.application.port.in.CarParkDistanceUseCase;
import com.wego.parkingspace.application.port.in.PopulateCarParkUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
class CarParkControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarParkDistanceUseCase carParkDistanceUseCase;
    @MockBean
    private PopulateCarParkUseCase populateCarParkUseCase;

    @Test
    void testUrlGetDistanceSuccess() throws Exception {
        mockMvc.perform(get("/get_car_parks").param("longitude", "103.98265048254667")
                .param("latitude", "1.3411134805883342")
                .param("per_page", "1")
                .param("page", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void testUrlGetDistanceLatitudeIsZeroThenThrowException() throws Exception {
        mockMvc.perform(get("/get_car_parks").param("longitude", "103.98265048254667")
                        .param("latitude", "0")
                        .param("per_page", "1")
                        .param("page", "1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUrlGetDistanceLongitudeIsZeroThenThrowException() throws Exception {
        mockMvc.perform(get("/get_car_parks").param("longitude", "0")
                        .param("latitude", "1.3411134805883342")
                        .param("per_page", "1")
                        .param("page", "1"))
                .andExpect(status().isBadRequest());
    }
}
