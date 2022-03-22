package com.trainingfresher.sampleservice.service.Section;

import com.trainingfresher.sampleservice.model.entity.Section;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService implements ISectionService {
    @Override
    public List<Section> findAll() {
        return null;
    }

    @Override
    public Optional<Section> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Section save(Section section) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
