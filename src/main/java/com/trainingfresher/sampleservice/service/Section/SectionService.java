package com.trainingfresher.sampleservice.service.Section;

import com.trainingfresher.sampleservice.model.entity.Section;
import com.trainingfresher.sampleservice.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService implements ISectionService {
    @Autowired
    private SectionRepository sectionRepository;
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
