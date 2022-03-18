package com.trainingfresher.sampleservice.dao;

import com.trainingfresher.sampleservice.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
}
