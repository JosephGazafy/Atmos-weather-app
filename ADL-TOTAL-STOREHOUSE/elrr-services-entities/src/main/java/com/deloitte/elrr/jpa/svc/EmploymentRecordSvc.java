package com.deloitte.elrr.jpa.svc;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.EmploymentRecord;
import com.deloitte.elrr.repository.EmploymentRecordRepository;

@Service
public class EmploymentRecordSvc implements CommonSvc<EmploymentRecord, UUID> {

    private final EmploymentRecordRepository employmentRecordRepository;

    /**
     *
     * @param argsEmploymentRecordRepository
     */
    public EmploymentRecordSvc(
            final EmploymentRecordRepository argsEmploymentRecordRepository) {
        this.employmentRecordRepository = argsEmploymentRecordRepository;
    }

    @Override
    public CrudRepository<EmploymentRecord, UUID> getRepository() {
        return this.employmentRecordRepository;
    }

    @Override
    public UUID getId(final EmploymentRecord employmentRecord) {
        return employmentRecord.getId();
    }

    @Override
    public EmploymentRecord save(final EmploymentRecord employmentRecord) {
        return CommonSvc.super.save(employmentRecord);
    }

    /**
     * Find employment records with optional filters using an
     * EmploymentRecord.Filter object.
     *
     * @param filter EmploymentRecord.Filter containing all filter criteria
     * @return List of employment records matching the criteria
     */
    public List<EmploymentRecord> findEmploymentRecordsWithFilters(
        EmploymentRecord.Filter filter) {
        return employmentRecordRepository
            .findEmploymentRecordsWithFilters(filter);
    }

}
