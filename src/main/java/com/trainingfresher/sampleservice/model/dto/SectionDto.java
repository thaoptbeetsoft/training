package com.trainingfresher.sampleservice.model.dto;

import com.trainingfresher.sampleservice.model.entity.Task;
import lombok.Data;

import java.util.Set;

@Data
public class SectionDto {
    private String name;
    private Long projectId;
    private Set<Task> tasks;
}
