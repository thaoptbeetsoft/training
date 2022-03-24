package com.trainingfresher.sampleservice.api.form;

import lombok.Data;
import lombok.Value;

import javax.validation.constraints.*;

@Data
public class ProjectForm {
    @NotBlank(message = "không được để trống và đồ dài từ 4 đến 30 kí tự")
    @Size(min = 4,max = 30)
    private String name;
    private Boolean enable;
    @NotNull(message = "không được để trống và id lớn hơn 0")
    @Min(1)
    private Long departmentId;

}
