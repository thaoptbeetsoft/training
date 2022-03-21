package com.trainingfresher.sampleservice.service;

import com.trainingfresher.sampleservice.api.form.DepartmentForm;
import com.trainingfresher.sampleservice.model.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department addNewDepartment(DepartmentForm department);

    Department updateDepartment(String name, Long id);

    void deleteDepartment(Long id);

    Department getById(Long id);

    Department getByName(String name);

    List<Department> getListDepartment();

    Department save(Department department);

}
