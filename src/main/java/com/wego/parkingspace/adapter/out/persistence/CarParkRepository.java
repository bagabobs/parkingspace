package com.wego.parkingspace.adapter.out.persistence;

import com.wego.parkingspace.adapter.out.persistence.generated.tables.pojos.CarParkEntities;
import com.wego.parkingspace.exceptions.RepositoryImplementationException;

import java.util.List;

public interface CarParkRepository extends ParkingSpaceRepository<String, CarParkEntities> {
    int saveCarParkEntitiesInBatch(List<CarParkEntities> carParkEntitiesList) throws RepositoryImplementationException;
    int updateCarParkEntitiesInBatch(List<CarParkEntities> carParkEntitiesList) throws RepositoryImplementationException;
    List<CarParkEntities> getCarParksByDistance(double longitude, double latitude, int page, int size) throws RepositoryImplementationException;
}
