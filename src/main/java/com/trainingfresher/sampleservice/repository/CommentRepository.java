package com.trainingfresher.sampleservice.repository;
import com.trainingfresher.sampleservice.model.entity.Comment;
import com.trainingfresher.sampleservice.model.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByTask(Task task);
}
