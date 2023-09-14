package com.wego.parkingspace;

import com.wego.parkingspace.application.port.in.CarParkDistanceUseCase;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
		properties = {"spring.test.database.replace=none", "spring.datasource.url=jdbc:tc:postgresql:15.3-alpine:///db"
		})
@AutoConfigureMockMvc
class ParkingspaceApplicationTests {
	@Autowired
	private MockMvc mvc;
	@Autowired
	private CarParkDistanceUseCase carParkDistanceUseCase;

	@Test
	void contextLoads() throws Exception {
		mvc.perform(get("/get_car_parks").param("longitude", "103.98265048254667")
						.param("latitude", "1.3411134805883342")
						.param("size", "1")
						.param("page", "1"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
                .andExpect(jsonPath("$[*].address", Matchers.contains("BLK 155/162 SIMEI ROAD")))
				.andExpect(jsonPath("$[*].total_lots", Matchers.anything()))
				.andExpect(jsonPath("$[*].available_lots", Matchers.anything()));
	}
}
