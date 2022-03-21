package com.trainingfresher.sampleservice.repository;

import com.trainingfresher.sampleservice.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    Department findByName(String name);

}

