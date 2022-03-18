package com.trainingfresher.sampleservice.dao;

import com.trainingfresher.sampleservice.model.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDao extends CrudRepository<Department, Long> {
}
