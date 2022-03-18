package com.trainingfresher.sampleservice.repository;

import com.trainingfresher.sampleservice.model.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
