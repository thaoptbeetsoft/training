package com.trainingfresher.sampleservice.repository;
import com.trainingfresher.sampleservice.model.entity.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends CrudRepository<Section, Long> {
    List<Section> findAllByProjectId(Long id);
}
