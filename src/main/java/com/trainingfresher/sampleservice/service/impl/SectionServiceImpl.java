package com.trainingfresher.sampleservice.service.impl;

import com.trainingfresher.sampleservice.model.dto.SectionDto;
import com.trainingfresher.sampleservice.model.entity.Section;
import com.trainingfresher.sampleservice.repository.ProjectRepository;
import com.trainingfresher.sampleservice.repository.SectionRepository;
import com.trainingfresher.sampleservice.service.SectionService;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

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
    public SectionDto convertSectionToDto(Section section) {
        SectionDto sectionDto =modelMapper.map(section,SectionDto.class);
        return sectionDto;
    }

    @Override
    public Section convertDtoToSection(SectionDto sectionDto) {
        Section section = modelMapper.map(sectionDto, Section.class);
        return section;
    }

    @Override
    public List<Section> findAllByProjectId(Long id) {
        return sectionRepository.findAllByProjectId(id);
    }

}
