package com.trainingfresher.sampleservice.model.dto;

import com.trainingfresher.sampleservice.model.entity.Task;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SectionDto {
    @NotNull
    @Size(min = 4, max = 30)
    private String name;
    @NotNull
    @Min(1)
    private Long projectId;
    private Set<Task> tasks;
}
