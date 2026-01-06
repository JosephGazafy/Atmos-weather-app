package com.deloitte.elrr.jpa.svc;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.LearningRecord;
import com.deloitte.elrr.repository.LearningRecordRepository;

@Service
public class LearningRecordSvc implements CommonSvc<LearningRecord, UUID> {

    private final LearningRecordRepository learningRecordRepository;

    /**
     * @param argsLearningRecordRepository
     */
    public LearningRecordSvc(
            final LearningRecordRepository argsLearningRecordRepository) {
        this.learningRecordRepository = argsLearningRecordRepository;
    }

    @Override
    public CrudRepository<LearningRecord, UUID> getRepository() {
        return this.learningRecordRepository;
    }

    @Override
    public UUID getId(final LearningRecord learningRecord) {
        return learningRecord.getId();
    }

    @Override
    public LearningRecord save(final LearningRecord learningRecord) {
        return CommonSvc.super.save(learningRecord);
    }

    /**
     * @param personId
     * @param learningResourceId
     * @return learningRecord
     */
    public LearningRecord findByPersonIdAndLearningResourceId(UUID personId,
            UUID learningResourceId) {
        LearningRecord learningRecord = learningRecordRepository
                .findByPersonIdAndLearningResourceId(personId,
                        learningResourceId);
        return learningRecord;
    }

    /**
     * Filter search.
     *
     * @param filter
     *            filter
     * @return learning records
     */
    public List<LearningRecord> findLearningRecordsWithFilters(
            final LearningRecord.Filter filter) {
        return learningRecordRepository.findLearningRecordsWithFilters(filter);
    }
}
