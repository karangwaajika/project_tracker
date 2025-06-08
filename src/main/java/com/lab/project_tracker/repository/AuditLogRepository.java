package com.lab.project_tracker.repository;

import com.lab.project_tracker.model.AuditLogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuditLogRepository extends MongoRepository<AuditLogEntity, Long> {
    List<AuditLogEntity> findByEntityType(String entityType);
    List<AuditLogEntity> findByActorName(String actorName);
    List<AuditLogEntity> findByEntityTypeAndActorName(String entityType, String actorName);
}
