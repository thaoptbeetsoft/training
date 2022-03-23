package com.trainingfresher.sampleservice.service;

import com.trainingfresher.sampleservice.model.entity.Section;

import java.util.List;
import java.util.Optional;

public interface SectionService {
    List<Section> findAllByProjectId(Long id);
    List<Section> findAll();
    Optional<Section> findById(Long id);
    Section save(Section section);
    void remove(Long id);
}
