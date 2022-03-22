package com.trainingfresher.sampleservice.service.department;

import com.trainingfresher.sampleservice.model.entity.Department;
import com.trainingfresher.sampleservice.model.entity.Section;
import com.trainingfresher.sampleservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public List<Section> findAll() {
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
