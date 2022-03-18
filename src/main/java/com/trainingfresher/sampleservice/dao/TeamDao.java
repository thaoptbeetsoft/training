package com.trainingfresher.sampleservice.dao;
import com.trainingfresher.sampleservice.model.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamDao extends CrudRepository<Team, Long> {
}
