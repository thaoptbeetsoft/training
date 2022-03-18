package com.trainingfresher.sampleservice.dao;

import com.trainingfresher.sampleservice.model.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDao extends CrudRepository<Project, Long> {
}
