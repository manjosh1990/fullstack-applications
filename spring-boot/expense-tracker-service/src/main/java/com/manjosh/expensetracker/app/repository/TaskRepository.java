package com.manjosh.expensetracker.app.repository;

import com.manjosh.expensetracker.app.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends MongoRepository<Task,String> {
    @Query("{'task_name':?0}")
    Optional<Task> findByName(String name);
}
