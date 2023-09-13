package com.wego.parkingspace.adapter.out.persistence;

import com.wego.parkingspace.exceptions.RepositoryImplementationException;

import java.util.List;

public interface ParkingSpaceRepository<T, V> {
    T save(V v) throws RepositoryImplementationException;

    T update(V v) throws RepositoryImplementationException;

    T delete(T id) throws RepositoryImplementationException;

    List<V> findAll() throws RepositoryImplementationException;
}
