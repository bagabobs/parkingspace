package com.wego.parkingspace.adapter.out.persistence;

import static com.wego.parkingspace.adapter.out.persistence.generated.Tables.CAR_PARK_ENTITIES;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.DSL.rad;

import com.wego.parkingspace.adapter.out.persistence.generated.tables.pojos.CarParkEntities;
import com.wego.parkingspace.domain.CarPark;
import com.wego.parkingspace.exceptions.RepositoryImplementationException;
import org.jooq.*;
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
                    CAR_PARK_ENTITIES.CAR_PARK_NUM, CAR_PARK_ENTITIES.ADDRESS, CAR_PARK_ENTITIES.LATITUDE,
                    CAR_PARK_ENTITIES.LONGITUDE, CAR_PARK_ENTITIES.AVAILABLE_LOTS,
                    CAR_PARK_ENTITIES.TOTAL_LOTS).values((String) null, null, null, null, null, null));
            carParkEntitiesList.forEach(carParkEntities -> batchBindStep.bind(carParkEntities.getCarParkNum(),
                    carParkEntities.getAddress(), carParkEntities.getLatitude(), carParkEntities.getLongitude(),
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
    public List<CarParkEntities> getCarParksByDistance(double longitude, double latitude, int page, int size) throws RepositoryImplementationException {
        Field<Double> fieldCalculation = val(6371.0)
                .mul(
                        acos(
                                cos(rad(val(latitude)))
                                        .mul(
                                                cos(rad(CAR_PARK_ENTITIES.LATITUDE))
                                        )
                                        .mul(
                                                cos(rad(val(longitude)).sub(rad(CAR_PARK_ENTITIES.LONGITUDE)))
                                        )
                                        .add(
                                                sin(rad(val(latitude))).mul(sin(rad(CAR_PARK_ENTITIES.LATITUDE)))
                                        )
                        )
                ).as("DISTANCE");
        return context.select( fieldCalculation,
                CAR_PARK_ENTITIES.CAR_PARK_NUM, CAR_PARK_ENTITIES.ADDRESS, CAR_PARK_ENTITIES.LONGITUDE,
                        CAR_PARK_ENTITIES.LATITUDE, CAR_PARK_ENTITIES.TOTAL_LOTS, CAR_PARK_ENTITIES.AVAILABLE_LOTS
        ).from(CAR_PARK_ENTITIES)
                .orderBy(fieldCalculation.asc())
                .limit(size)
                .offset(page * size)
                .fetch()
                .stream()
                .map(record -> new CarParkEntities(record.value2(), record.value3(), record.value5(), record.value4(),
                        record.value6(), record.value7())).toList();
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
