package com.trainingfresher.sampleservice.dao;
import com.trainingfresher.sampleservice.model.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao  extends CrudRepository<Comment, Long> {


}
