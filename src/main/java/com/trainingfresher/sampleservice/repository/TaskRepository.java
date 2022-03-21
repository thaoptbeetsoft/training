package com.trainingfresher.sampleservice.repository;
import com.trainingfresher.sampleservice.model.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Task findByName(String name);

    @Query("SELECT t FROM Task t " +
            "JOIN Section s ON t.section = s.id " +
            "JOIN Project p ON s.project = p.id " +
            "WHERE s.id = :sectionId AND p.id = :projectId")
    List<Task> getListTaskInSection(Long projectId, Long sectionId);

    @Query("SELECT t FROM Task t WHERE t.project = :projectId ")
    List<Task> getListTaskNonSection(Long projectId);
}
