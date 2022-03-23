package com.trainingfresher.sampleservice.repository;
import com.trainingfresher.sampleservice.model.entity.Project;
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
    List<Task> getListTaskInSectionAndProject(Long projectId, Long sectionId);

    List<Task> findByProject(Project project);

    @Query(value = "SELECT t FROM tasks t WHERE t.project_id AND t.section_id IS NULL", nativeQuery = true)
    List<Task> getListTaskPersonal();
}
