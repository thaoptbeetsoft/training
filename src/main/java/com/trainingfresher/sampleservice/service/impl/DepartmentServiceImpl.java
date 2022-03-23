package com.trainingfresher.sampleservice.service.impl;

import com.trainingfresher.sampleservice.model.entity.Department;
import com.trainingfresher.sampleservice.model.entity.Section;
import com.trainingfresher.sampleservice.repository.DepartmentRepository;
import com.trainingfresher.sampleservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public List<Department> findAll() {
        return null;
    }

    @Override
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department save(Department department) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
