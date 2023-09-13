package com.wego.parkingspace.adapter.out.persistence;

import com.wego.parkingspace.adapter.out.persistence.generated.tables.pojos.CarParkEntities;
import com.wego.parkingspace.exceptions.RepositoryImplementationException;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;

import java.util.List;

import static com.wego.parkingspace.adapter.out.persistence.generated.Tables.CAR_PARK_ENTITIES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.jooq.impl.DSL.count;

@JooqTest(
        properties = {"spring.test.database.replace=none", "spring.datasource.url=jdbc:tc:postgresql:15.3-alpine:///db"
        })
public class CarParkRepositoryTest {
    @Autowired
    private DSLContext context;
    private CarParkRepository carParkRepository;

    @BeforeEach
    void initialSetup() {
        carParkRepository = new CarParkRepositoryImpl(context);
    }

    @Test
    void saveCarParkEntitiesInBatchHaveSameSizeWithSelectQuery() throws RepositoryImplementationException {
        Integer count = context.select(count()).from(CAR_PARK_ENTITIES).fetchOneInto(Integer.class);
        CarParkEntities carParkEntityOne = new CarParkEntities();
        carParkEntityOne.setCarParkNum("1000");
        carParkEntityOne.setAddress("Home Address One");
        carParkEntityOne.setXCoordinate(1.0);
        carParkEntityOne.setYCoordinate(1.0);
        carParkEntityOne.setAvailableLots(10);
        carParkEntityOne.setTotalLots(15);

        CarParkEntities carParkEntityTwo = new CarParkEntities();
        carParkEntityTwo.setCarParkNum("1001");
        carParkEntityTwo.setAddress("Home Address Two");
        carParkEntityTwo.setXCoordinate(1.0);
        carParkEntityTwo.setYCoordinate(1.0);
        carParkEntityTwo.setAvailableLots(10);
        carParkEntityTwo.setTotalLots(15);

        List<CarParkEntities> carParkEntitiesList = List.of(carParkEntityOne, carParkEntityTwo);

        int result = carParkRepository.saveCarParkEntitiesInBatch(carParkEntitiesList);

        List<CarParkEntities> carParkEntitiesListResult = context.selectFrom(CAR_PARK_ENTITIES)
                .fetchInto(CarParkEntities.class);
        assertThat(carParkEntitiesListResult.size()).isEqualTo(result + count);
    }

    @Test
    void saveCarParkEntitiesInBatchThrowsExceptionWhenDuplicateKeys() {
        CarParkEntities carParkEntityOne = new CarParkEntities();
        carParkEntityOne.setCarParkNum("1000");
        carParkEntityOne.setAddress("Home Address One");
        carParkEntityOne.setXCoordinate(1.0);
        carParkEntityOne.setYCoordinate(1.0);
        carParkEntityOne.setAvailableLots(10);
        carParkEntityOne.setTotalLots(15);

        CarParkEntities carParkEntityTwo = new CarParkEntities();
        carParkEntityTwo.setCarParkNum("1000");
        carParkEntityTwo.setAddress("Home Address Two");
        carParkEntityTwo.setXCoordinate(1.0);
        carParkEntityTwo.setYCoordinate(1.0);
        carParkEntityTwo.setAvailableLots(10);
        carParkEntityTwo.setTotalLots(15);

        List<CarParkEntities> carParkEntitiesList = List.of(carParkEntityOne, carParkEntityTwo);

        assertThatThrownBy(() -> carParkRepository.saveCarParkEntitiesInBatch(carParkEntitiesList))
                .isInstanceOf(RepositoryImplementationException.class)
                .hasMessage("Duplicate key exception");
    }

    @Test
    void saveCarParkEntitiesInBatchThrowsExceptionWhenViolatesNotNullConstraint() {
        CarParkEntities carParkEntityOne = new CarParkEntities();
        carParkEntityOne.setCarParkNum("1000");
        carParkEntityOne.setAddress(null);
        carParkEntityOne.setXCoordinate(1.0);
        carParkEntityOne.setYCoordinate(1.0);
        carParkEntityOne.setAvailableLots(10);
        carParkEntityOne.setTotalLots(15);

        List<CarParkEntities> carParkEntitiesList = List.of(carParkEntityOne);
        assertThatThrownBy(() -> carParkRepository.saveCarParkEntitiesInBatch(carParkEntitiesList))
                .isInstanceOf(RepositoryImplementationException.class)
                .hasMessage("Insert with null value violates not null constraint exception");
    }

    @Test
    void updateCarParkEntitiesInBatchUpdateSuccess() throws RepositoryImplementationException {
        CarParkEntities carParkEntityOne = new CarParkEntities();
        carParkEntityOne.setCarParkNum("1000");
        carParkEntityOne.setAddress("Home Address One");
        carParkEntityOne.setXCoordinate(1.0);
        carParkEntityOne.setYCoordinate(1.0);
        carParkEntityOne.setAvailableLots(10);
        carParkEntityOne.setTotalLots(15);

        CarParkEntities carParkEntityTwo = new CarParkEntities();
        carParkEntityTwo.setCarParkNum("1001");
        carParkEntityTwo.setAddress("Home Address Two");
        carParkEntityTwo.setXCoordinate(1.0);
        carParkEntityTwo.setYCoordinate(1.0);
        carParkEntityTwo.setAvailableLots(10);
        carParkEntityTwo.setTotalLots(15);

        List<CarParkEntities> carParkEntitiesList = List.of(carParkEntityOne, carParkEntityTwo);

        int result = carParkRepository.saveCarParkEntitiesInBatch(carParkEntitiesList);

        carParkEntityOne = carParkEntitiesList.get(0);
        carParkEntityOne.setTotalLots(15);
        carParkEntityOne.setAvailableLots(13);

        result = carParkRepository.updateCarParkEntitiesInBatch(carParkEntitiesList);
        CarParkEntities carParkEntities = context.selectFrom(CAR_PARK_ENTITIES).where(CAR_PARK_ENTITIES.CAR_PARK_NUM.eq(carParkEntityOne.getCarParkNum())).fetchOneInto(CarParkEntities.class);
        assertThat(carParkEntities.getTotalLots()).isEqualTo(carParkEntityOne.getTotalLots());
    }
}
