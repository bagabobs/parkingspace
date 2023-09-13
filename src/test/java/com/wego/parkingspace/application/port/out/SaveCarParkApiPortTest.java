package com.wego.parkingspace.application.port.out;

import static org.assertj.core.api.Assertions.*;
import com.wego.parkingspace.adapter.out.persistence.CarParkPersistenceAdapter;
import com.wego.parkingspace.adapter.out.persistence.CarParkRepository;
import com.wego.parkingspace.adapter.out.persistence.generated.tables.pojos.CarParkEntities;
import com.wego.parkingspace.domain.CarPark;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaveCarParkApiPortTest {
    @Mock
    private CarParkRepository carParkRepository;
    private SaveCarParkPort saveCarParkPort;

    @BeforeEach
    void initialSetup() {
        saveCarParkPort = new CarParkPersistenceAdapter(carParkRepository);
    }

    @Test
    void saveInBatchSuccess() throws Exception {
        CarPark carParkOne = new CarPark("1000", "Home Address One", 1.2, 1.3,
                10, 15);
        CarPark carParkTwo = new CarPark("1001", "Home Address Two", 1.3, 1.4,
                11, 13);
        List<CarPark> carParks = List.of(carParkOne, carParkTwo);

        CarParkEntities carParkEntitiesOne = new CarParkEntities("1000", "Home Address One",
                1.2, 1.3, 10, 15);
        CarParkEntities carParkEntitiesTwo = new CarParkEntities("1001", "Home Address Two",
                1.3, 1.4, 11, 13);
        List<CarParkEntities> carParkEntitiesList = List.of(carParkEntitiesOne, carParkEntitiesTwo);

        when(carParkRepository.saveCarParkEntitiesInBatch(carParkEntitiesList)).thenReturn(carParkEntitiesList.size());

        int resultSize = saveCarParkPort.saveInBatch(carParks);
        verify(carParkRepository).saveCarParkEntitiesInBatch(carParkEntitiesList);

        assertThat(resultSize).isEqualTo(carParks.size());
    }
}
