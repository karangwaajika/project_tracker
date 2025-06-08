package com.lab.project_tracker.service;

import com.lab.project_tracker.model.AuditLogEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AuditLogService {
    void logAction(String actionType, String entityType, String entityId, String actor, Map<String, Object> payload);
}
