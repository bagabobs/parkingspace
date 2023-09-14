package com.wego.parkingspace.application.service;

import static com.wego.parkingspace.adapter.out.persistence.generated.Tables.CAR_PARK_ENTITIES;
import static org.jooq.impl.DSL.*;

import com.wego.parkingspace.adapter.out.persistence.generated.tables.pojos.CarParkEntities;
import com.wego.parkingspace.application.port.in.PopulateCarParkUseCase;
import com.wego.parkingspace.exceptions.PopulateException;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record7;
import org.jooq.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//@SpringBootTest(
//        properties = {"spring.test.database.replace=none", "spring.datasource.url=jdbc:tc:postgresql:15.3-alpine:///db"
//        })
public class LatLongConversionServiceTest {
//    @Autowired
//    private PopulateCarParkUseCase populateCarParkUseCase;
//    @Autowired
//    private DSLContext dslContext;
//
//
//    @Test
//    void test() throws Exception {
//        populateCarParkUseCase.populate();
//        Field<Double> fieldCalculation = val(6371.0)
//                .mul(
//                        acos(
//                                cos(rad(val(103.98845411052076)))
//                                        .mul(
//                                                cos(rad(CAR_PARK_ENTITIES.LATITUDE))
//                                        )
//                                        .mul(
//                                                cos(rad(val(1.3901710996794703)).sub(rad(CAR_PARK_ENTITIES.LONGITUDE)))
//                                        )
//                                        .add(
//                                                sin(rad(val(103.98845411052076))).mul(sin(rad(CAR_PARK_ENTITIES.LATITUDE)))
//                                        )
//                        )
//                ).as("DISTANCE");
//        Result<Record7<Double, String, String, Double, Double, Integer, Integer>> carParkList = dslContext.select( fieldCalculation,
//                CAR_PARK_ENTITIES.CAR_PARK_NUM, CAR_PARK_ENTITIES.ADDRESS, CAR_PARK_ENTITIES.LONGITUDE, CAR_PARK_ENTITIES.LATITUDE, CAR_PARK_ENTITIES.TOTAL_LOTS, CAR_PARK_ENTITIES.AVAILABLE_LOTS
//                ).from(CAR_PARK_ENTITIES).orderBy(fieldCalculation.asc()).limit(10).fetch();
//        carParkList.forEach(System.out::println);
//    }
}
