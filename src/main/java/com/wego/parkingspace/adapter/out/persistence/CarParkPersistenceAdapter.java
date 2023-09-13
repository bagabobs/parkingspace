package com.wego.parkingspace.adapter.out.persistence;

import com.wego.parkingspace.adapter.out.persistence.generated.tables.pojos.CarParkEntities;
import com.wego.parkingspace.application.port.out.SaveCarParkPort;
import com.wego.parkingspace.domain.CarPark;
import com.wego.parkingspace.exceptions.PersistenceAdapterException;
import com.wego.parkingspace.exceptions.RepositoryImplementationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarParkPersistenceAdapter implements SaveCarParkPort {
    private final CarParkRepository carParkRepository;

    public CarParkPersistenceAdapter(CarParkRepository carParkRepository) {
        this.carParkRepository = carParkRepository;
    }

    @Override
    public int saveInBatch(List<CarPark> carParks) throws PersistenceAdapterException {
        try {
            List<CarParkEntities> carParkEntitiesList = carParks.stream().map(carPark ->
                    new CarParkEntities(carPark.getCarParkNumber(), carPark.getAddress(), carPark.getLatitude(),
                            carPark.getLongitude(), carPark.getTotalLots(), carPark.getAvailableLots()))
                    .toList();
            return carParkRepository.saveCarParkEntitiesInBatch(carParkEntitiesList);
        } catch(RepositoryImplementationException exception) {
            throw new PersistenceAdapterException(exception.getMessage(), exception);
        }
    }

    @Override
    public int updateInBatch(List<CarPark> carParks) throws PersistenceAdapterException {
        try {
            List<CarParkEntities> carParkEntitiesList = carParks.stream().map(carPark ->
                            new CarParkEntities(carPark.getCarParkNumber(), carPark.getAddress(), carPark.getLatitude(),
                                    carPark.getLongitude(), carPark.getTotalLots(), carPark.getAvailableLots()))
                    .toList();
            return carParkRepository.updateCarParkEntitiesInBatch(carParkEntitiesList);
        } catch(RepositoryImplementationException exception) {
            throw new PersistenceAdapterException(exception.getMessage(), exception);
        }
    }
}
