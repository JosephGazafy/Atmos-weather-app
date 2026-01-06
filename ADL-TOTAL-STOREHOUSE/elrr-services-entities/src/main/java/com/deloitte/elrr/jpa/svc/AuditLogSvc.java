package com.deloitte.elrr.jpa.svc;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.AuditLog;
import com.deloitte.elrr.repository.AuditLogRepository;

@Service
public class AuditLogSvc implements CommonSvc<AuditLog, UUID> {
    private final AuditLogRepository auditLogRepository;

    /**
     * Constructor for AuditLogSvc.
     *
     * @param argsAuditLogRepository the audit log repository
     */
    public AuditLogSvc(final AuditLogRepository argsAuditLogRepository) {
        this.auditLogRepository = argsAuditLogRepository;
    }

    @Override
    public CrudRepository<AuditLog, UUID> getRepository() {
        return this.auditLogRepository;
    }

    @Override
    public UUID getId(final AuditLog auditLog) {
        return auditLog.getId();
    }

    @Override
    public AuditLog save(final AuditLog auditLog) {
        return CommonSvc.super.save(auditLog);
    }

    /**
     * Audit logs should never be deleted to maintain data integrity
     * and compliance requirements.
     *
     * @param id the ID of the audit log entry
     * @throws UnsupportedOperationException always thrown to prevent deletion
     */
    @Override
    public void delete(final UUID id) {
        throw new UnsupportedOperationException(
                "Audit log entries cannot be deleted for compliance reasons");
    }

    /**
     * Audit logs should never be deleted to maintain data integrity
     * and compliance requirements.
     *
     * @throws UnsupportedOperationException always thrown to prevent deletion
     */
    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException(
                "Audit log entries cannot be deleted for compliance reasons");
    }

    /**
     * Audit logs should never be updated to maintain data integrity
     * and compliance requirements. Once created, audit entries are immutable.
     *
     * @param auditLog the audit log entity
     * @throws UnsupportedOperationException always thrown to prevent updates
     */
    @Override
    public void update(final AuditLog auditLog) {
        throw new UnsupportedOperationException(
                "Audit log entries cannot be updated for compliance reasons");
    }
}
