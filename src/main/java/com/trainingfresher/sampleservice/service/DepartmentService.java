package com.trainingfresher.sampleservice.service;

import com.trainingfresher.sampleservice.model.entity.Department;
import com.trainingfresher.sampleservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    public Department saveDepartment(Department department) {
        return repository.save(department);
    }

    public List<Department> saveDepartments(List<Department> departments) {
        return (List<Department>) repository.saveAll(departments);
    }

    public List<Department> getDepartments() {
        return (List<Department>) repository.findAll();
    }

    public Department getDepartmentById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Department getDepartmentByName(String name) {
        return repository.findByName(name);
    }

    public String deleteDepartment(long id) {
        repository.deleteById(id);
        return "department removed ||" + id;
    }

    public Department updateDepartment(Department department) {
        Department existingDepartment = repository.findById(department.getId()).orElse(null);
        existingDepartment.setName(department.getName());
        existingDepartment.setProjects(department.getProjects());
        existingDepartment.setTeams(department.getTeams());
        return repository.save(existingDepartment);
    }
}
