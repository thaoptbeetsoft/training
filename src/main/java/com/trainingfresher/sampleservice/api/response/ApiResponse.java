package com.trainingfresher.sampleservice.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
public class ApiResponse {
    private String status;

    private int code;
    private String message;
    private Object data;


    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static ApiResponse appendSuccess(Object data, int code, String message) {
        Object dataTmp = new ArrayList<>();
        if (!ObjectUtils.isEmpty(data)) {
            dataTmp = data;
        }
        return ApiResponse.builder()
                .status("SUCCESS")
                .message(StringUtils.hasText(message) ? message : "")
                .code(code)
                .data(dataTmp)
                .build();
    }

}
