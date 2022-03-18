package com.trainingfresher.sampleservice.repository;

import com.trainingfresher.sampleservice.model.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
