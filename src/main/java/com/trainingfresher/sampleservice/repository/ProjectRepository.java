package com.trainingfresher.sampleservice.repository;

import com.trainingfresher.sampleservice.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "SELECT * FROM projects " +
            "INNER JOIN project_team " +
            "ON projects.id = project_team.project_id " +
            "INNER JOIN teams " +
            "    ON project_team.team_id = teams.id " +
            "INNER JOIN team_user " +
            "    ON teams.id = team_user.team_id " +
            "INNER JOIN users " +
            "    ON team_user.user_id = users.id " +
            "WHERE users.id = :id ", nativeQuery = true)
    List<Project> findAllByUserIdAndDepartmentId(Long id);

    @Query(value = "SELECT * FROM projects p " +
            "INNER JOIN department_project dp ON p.id = dp.project_id " +
            "INNER JOIN departments d ON dp.project_id = d.id " +
            "WHERE d.id = :id", nativeQuery = true)
    List<Project> findAllByDepartmentId(Long id);
}
