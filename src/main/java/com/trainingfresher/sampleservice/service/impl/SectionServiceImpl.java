package com.trainingfresher.sampleservice.service.impl;

import com.trainingfresher.sampleservice.model.entity.Section;
import com.trainingfresher.sampleservice.repository.ProjectRepository;
import com.trainingfresher.sampleservice.repository.SectionRepository;
import com.trainingfresher.sampleservice.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionServiceImpl implements SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Section> findAll() {
        return (List<Section>) sectionRepository.findAll();
    }

    @Override
    public Optional<Section> findById(Long id) {
        return sectionRepository.findById(id);
    }

    @Override
    public Section save(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public List<Section> findAllByProjectId(Long id) {
        return sectionRepository.findAllByProjectId(id);
    }

}
