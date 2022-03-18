package com.trainingfresher.sampleservice.repository;
import com.trainingfresher.sampleservice.model.entity.Section;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends CrudRepository<Section, Long> {
}
