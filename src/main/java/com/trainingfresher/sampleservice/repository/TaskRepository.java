package com.trainingfresher.sampleservice.repository;
import com.trainingfresher.sampleservice.model.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
