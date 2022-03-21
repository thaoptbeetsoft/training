package com.trainingfresher.sampleservice.repository;

import com.trainingfresher.sampleservice.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "SELECT DISTINCT p.id, p.name from projects p\n" +
            "inner join .department_project dp\n" +
            "    on p.id = dp.project_id\n" +
            "inner join departments d \n" +
            "    on dp.project_id = d.id\n" +
            "where d.id = :id; ", nativeQuery = true)
    Iterable<Project> findAllByDepartment_id(Long id);


    @Query(value = "select projects.id,project.name from projects " +
            "inner join project_team " +
            "    on projects.id = project_team.project_id" +
            "inner join teams " +
            "    on project_team.team_id = teams.id" +
            "inner join team_user " +
            "    on teams.id = team_user.team_id" +
            "inner join users " +
            "    on team_user.user_id=users.id" +
            "where users.id = :id ", nativeQuery = true)
    Iterable<Project> findAllByUser_IdEndDepartment_id(Long id);


}
