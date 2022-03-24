package com.trainingfresher.sampleservice.repository;

import com.trainingfresher.sampleservice.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "select * from projects p " +
            "join department_project dp on dp.project_id=p.id " +
            "join departments d on d.id = dp.department_id " +
            "where d.id = :id ", nativeQuery = true)
    List<Project> findAllByDepartmentId(Long id);
}