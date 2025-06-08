package com.lab.project_tracker.repository;

import com.lab.project_tracker.model.AuditLogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditLogRepository extends MongoRepository<AuditLogEntity, Long> {
}
