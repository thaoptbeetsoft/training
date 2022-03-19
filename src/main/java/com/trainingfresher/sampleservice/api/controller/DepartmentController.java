package com.trainingfresher.sampleservice.api.controller;

import com.trainingfresher.sampleservice.model.entity.Department;
import com.trainingfresher.sampleservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @PostMapping("/addDepartment")
    public Department addDepartment(@RequestBody Department department) {
        return service.saveDepartment(department);
    }

    @PostMapping("/addDepartments")
    public List<Department> addDepartments(@RequestBody List<Department> departments) {
        return service.saveDepartments(departments);
    }

    @GetMapping("/departments")
    public List<Department> findAllProducts() {
        return service.getDepartments();
    }

    @GetMapping("/departmentByName/{name}")
    public Department findDepartmentByName(@PathVariable String name) {
        return service.getDepartmentByName(name);
    }

    @GetMapping("/departmentById/{id}")
    public Department findDepartmentById(@PathVariable long id) {
        return service.getDepartmentById(id);
    }

    @PutMapping("/update")
    public Department updateDepartment(@RequestBody Department department) {
        return service.updateDepartment(department);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable long id) {
        return service.deleteDepartment(id);
    }

}
