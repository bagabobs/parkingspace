package com.wego.parkingspace;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
		properties = {"spring.test.database.replace=none", "spring.datasource.url=jdbc:tc:postgresql:15.3-alpine:///db"
		})
class ParkingspaceApplicationTests {

	@Test
	void contextLoads() {
	}

}
