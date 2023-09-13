package com.wego.parkingspace.adapter.out.persistence;

import static com.wego.parkingspace.adapter.out.persistence.generated.Tables.CAR_PARK_ENTITIES;

import com.wego.parkingspace.adapter.out.persistence.generated.tables.pojos.CarParkEntities;
import com.wego.parkingspace.exceptions.RepositoryImplementationException;
import org.jooq.BatchBindStep;
import org.jooq.DSLContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CarParkRepositoryImpl implements CarParkRepository {
    private final DSLContext context;

    public CarParkRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    @Override
    public int saveCarParkEntitiesInBatch(List<CarParkEntities> carParkEntitiesList)
            throws RepositoryImplementationException {
        try {
            BatchBindStep batchBindStep = context.batch(context.insertInto(CAR_PARK_ENTITIES,
                    CAR_PARK_ENTITIES.CAR_PARK_NUM, CAR_PARK_ENTITIES.ADDRESS, CAR_PARK_ENTITIES.X_COORDINATE,
                    CAR_PARK_ENTITIES.Y_COORDINATE, CAR_PARK_ENTITIES.AVAILABLE_LOTS,
                    CAR_PARK_ENTITIES.TOTAL_LOTS).values((String) null, null, null, null, null, null));
            carParkEntitiesList.forEach(carParkEntities -> batchBindStep.bind(carParkEntities.getCarParkNum(),
                    carParkEntities.getAddress(), carParkEntities.getXCoordinate(), carParkEntities.getYCoordinate(),
                    carParkEntities.getAvailableLots(), carParkEntities.getTotalLots()));
            return batchBindStep.execute().length;
        } catch(DuplicateKeyException duplicateKeyException) {
            throw new RepositoryImplementationException("Duplicate key exception", duplicateKeyException);
        } catch(DataIntegrityViolationException dataIntegrityViolationException) {
            throw new RepositoryImplementationException("Insert with null value violates not null constraint exception",
                    dataIntegrityViolationException);
        }
    }

    @Override
    public int updateCarParkEntitiesInBatch(List<CarParkEntities> carParkEntitiesList)
            throws RepositoryImplementationException {
        try {
            BatchBindStep batchBindStep = context.batch(context.update(CAR_PARK_ENTITIES)
                    .set(CAR_PARK_ENTITIES.TOTAL_LOTS, (Integer) null)
                    .set(CAR_PARK_ENTITIES.AVAILABLE_LOTS, (Integer) null)
                    .where(CAR_PARK_ENTITIES.CAR_PARK_NUM.eq((String) null)));
            carParkEntitiesList.forEach(carParkEntities -> batchBindStep.bind(carParkEntities.getTotalLots(),
                    carParkEntities.getAvailableLots(), carParkEntities.getCarParkNum()));
            return batchBindStep.execute().length;
        } catch(DataIntegrityViolationException dataIntegrityViolationException) {
            throw new RepositoryImplementationException("Update with null value violates not null constraint exception",
                    dataIntegrityViolationException);
        }
    }

    @Override
    public String save(CarParkEntities carParkEntities) throws RepositoryImplementationException {
        return null;
    }

    @Override
    public String update(CarParkEntities carParkEntities) throws RepositoryImplementationException {
        return null;
    }

    @Override
    public String delete(String id) throws RepositoryImplementationException {
        return null;
    }

    @Override
    public List<CarParkEntities> findAll() throws RepositoryImplementationException {
        return null;
    }
}
