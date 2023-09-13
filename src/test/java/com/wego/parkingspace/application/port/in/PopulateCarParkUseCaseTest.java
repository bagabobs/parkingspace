package com.wego.parkingspace.application.port.in;

import static com.wego.parkingspace.adapter.out.persistence.generated.Tables.CAR_PARK_ENTITIES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.jooq.impl.DSL.count;

import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        properties = {"spring.test.database.replace=none", "spring.datasource.url=jdbc:tc:postgresql:15.3-alpine:///db"
        })
public class PopulateCarParkUseCaseTest {
    @Autowired
    private PopulateCarParkUseCase populateCarParkUseCase;
    @Autowired
    private DSLContext context;

    @BeforeEach
    void initialSetup() {
        context.truncate(CAR_PARK_ENTITIES);
    }

    @Test
    void testPopulateCarParks() throws Exception {
        Integer countBeforePopulate = context.select(count()).from(CAR_PARK_ENTITIES).fetchOneInto(Integer.class);
        int populateSize = populateCarParkUseCase.populate();
        Integer countAfterPopulate = context.select(count()).from(CAR_PARK_ENTITIES).fetchOneInto(Integer.class);

        assertThat(populateSize).isEqualTo(countAfterPopulate - countBeforePopulate);
    }
}
