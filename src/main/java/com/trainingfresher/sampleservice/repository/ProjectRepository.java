package com.trainingfresher.sampleservice.repository;

import com.trainingfresher.sampleservice.model.entity.Department;
import com.trainingfresher.sampleservice.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT * FROM projects p " +
            "INNER JOIN department_project dp ON p.id = dp.project_id " +
            "INNER JOIN departments d ON dp.project_id = d.id " +
            "WHERE d.id = :id", nativeQuery = true)
    List<Project> findAllByDepartmentId(Long id);
}
