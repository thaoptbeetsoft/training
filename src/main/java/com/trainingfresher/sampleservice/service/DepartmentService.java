package com.trainingfresher.sampleservice.service;

import com.trainingfresher.sampleservice.model.entity.Department;
import java.util.List;
import java.util.Optional;

public interface DepartmentService{
    List<Department> findAll();

    Optional<Department> findById(Long id);

    Department save(Department department);

    void remove(Long id);
}
