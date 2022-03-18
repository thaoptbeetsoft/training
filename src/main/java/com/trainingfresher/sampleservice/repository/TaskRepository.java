package com.trainingfresher.sampleservice.repository;
import com.trainingfresher.sampleservice.model.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Task findByName(String name);

    @Query(value = "SELECT t FROM Task t JOIN Section s ON t.section_id = s.id JOIN Project p ON s.project_id = p.id WHERE s.id = ?2 AND p.id = ?1", nativeQuery = true)
    List<Task> getListTaskInSection(Long projectId, Long sectionId);

    @Query(value = "SELECT t FROM Task t WHERE t.project_id = ?1 ", nativeQuery = true)
    List<Task> getListTaskNonSection(Long projectId);
}
