package com.trainingfresher.sampleservice.repository;
import com.trainingfresher.sampleservice.model.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
}
