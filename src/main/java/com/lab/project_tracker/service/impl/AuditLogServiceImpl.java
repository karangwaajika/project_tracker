package com.lab.project_tracker.service.impl;

import com.lab.project_tracker.model.AuditLogEntity;
import com.lab.project_tracker.repository.AuditLogRepository;
import com.lab.project_tracker.service.AuditLogService;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AuditLogServiceImpl implements AuditLogService {

    private AuditLogRepository auditLogRepository;
    public AuditLogServiceImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public void logAction(String actionType, String entityType, String entityId, String actor, Map<String, Object> payload) {
    }

    @Override
    public List<AuditLogEntity> getLogs(Optional<String> entityType, Optional<String> actorName) {
        return null;
    }

}
