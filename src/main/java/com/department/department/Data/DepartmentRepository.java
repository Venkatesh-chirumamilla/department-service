package com.department.department.Data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Long> {
    DepartmentEntity findByName(String name);
    Iterable<DepartmentEntity> findByCity(String city);
    Iterable<DepartmentEntity> findByState(String state);
    Iterable<DepartmentEntity> findByCountry(String country);
    Iterable<DepartmentEntity> findByZipcode(String zipcode);
    void deleteByName(String name);

    Iterable<DepartmentEntity> findByStateAndCity(String state, String city);
}