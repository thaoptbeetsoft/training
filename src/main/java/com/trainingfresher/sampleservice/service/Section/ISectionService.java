package com.trainingfresher.sampleservice.service.Section;

import com.trainingfresher.sampleservice.model.entity.Section;
import com.trainingfresher.sampleservice.service.IGeneralService;

import java.util.List;

public interface ISectionService extends IGeneralService<Section> {
    List<Section> findAllByProjectId(Long id);
}
