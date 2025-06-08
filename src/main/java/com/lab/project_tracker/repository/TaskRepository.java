package com.lab.project_tracker.repository;

import com.lab.project_tracker.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Optional<TaskEntity> findTaskEntitiesByTitle(String title);
    List<TaskEntity> findAllByOrderByDueDateAsc();
    @Query("SELECT t FROM TaskEntity t WHERE t.dueDate < CURRENT_DATE")
    List<TaskEntity> findOverdueTasks();
}
