package com.trainingfresher.sampleservice.dao;
import com.trainingfresher.sampleservice.model.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao extends CrudRepository<Task, Long> {
}
