package com.trainingfresher.sampleservice.api.form;

import com.trainingfresher.sampleservice.model.entity.Comment;
import lombok.Data;

@Data
public class CommentForm {

    private String text;
    private Long parentId;
    private Comment subComment;
}
