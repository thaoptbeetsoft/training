package com.trainingfresher.sampleservice.service;

import com.trainingfresher.sampleservice.model.entity.Section;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T> {
    List<Section> findAll();
    Optional<T>findById(Long id);
    T save(T t);
    void remove(Long id);

}
