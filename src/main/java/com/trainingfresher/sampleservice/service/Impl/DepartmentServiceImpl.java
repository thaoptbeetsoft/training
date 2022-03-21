package com.trainingfresher.sampleservice.service.Impl;

import com.trainingfresher.sampleservice.api.form.DepartmentForm;
import com.trainingfresher.sampleservice.model.entity.Department;
import com.trainingfresher.sampleservice.repository.DepartmentRepository;
import com.trainingfresher.sampleservice.service.DepartmentService;
import com.trainingfresher.sampleservice.utils.exception.BadRequestException;
import com.trainingfresher.sampleservice.utils.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department addNewDepartment(DepartmentForm data) {
        if(!StringUtils.hasText(data.getName())) {
            String mess = "Invalid argument";
            throw new BadRequestException(mess);
        }

        Department department = departmentRepository.findByName(data.getName());

        if(!ObjectUtils.isEmpty(department)) {
            String mess = "department-not-exits";
            throw new NotFoundException(mess);
        }

        department = Department.builder()
                .name(data.getName())
                .build();

        return save(department);
    }

    @Override
    public Department updateDepartment(String name, Long id) {
        Department department = departmentRepository.findById(id).get();

        if(ObjectUtils.isEmpty(department)) {
            String mess = "department-not-exits";
            throw new NotFoundException(mess);
        }

        Department department1 = departmentRepository.findByName(name);

        if(!ObjectUtils.isEmpty(department1)) {
            String mess = "department-not-exits";
            throw new NotFoundException(mess);
        }

        department.setName(name);

        return save(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).get();

        if(ObjectUtils.isEmpty(department)) {
            String mess = "department-not-exits";
            throw new NotFoundException(mess);
        }

        departmentRepository.deleteById(id);
    }

    @Override
    public Department getById(Long id) {
        Department department = departmentRepository.findById(id).get();

        if(ObjectUtils.isEmpty(department)) {
            String mess = "department-not-exits";
            throw new NotFoundException(mess);
        }

        return department;
    }

    @Override
    public Department getByName(String name) {
        Department department = departmentRepository.findByName(name);
        if (ObjectUtils.isEmpty(department)) {
            String mess = "department_not_exist";
            throw new NotFoundException(mess);
        }
        return department;
    }

    @Override
    public List<Department> getListDepartment() {
        List<Department> departments = (List<Department>) departmentRepository.findAll();
        if(ObjectUtils.isEmpty(departments)) {
            return Collections.EMPTY_LIST;
        }

        return departments;
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }
}
