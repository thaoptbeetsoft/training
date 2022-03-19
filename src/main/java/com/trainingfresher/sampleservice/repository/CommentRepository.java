package com.trainingfresher.sampleservice.repository;
import com.trainingfresher.sampleservice.model.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByTask(Long taskId);
}
