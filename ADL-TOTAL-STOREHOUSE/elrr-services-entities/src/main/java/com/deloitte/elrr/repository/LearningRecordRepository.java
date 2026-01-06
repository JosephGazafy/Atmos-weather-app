package com.deloitte.elrr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deloitte.elrr.entity.LearningRecord;

@Repository
public interface LearningRecordRepository extends JpaRepository<LearningRecord,
        UUID> {

    /**
     * @param personId
     * @param learningResourceId
     * @return LearningRecord
     */
    LearningRecord findByPersonIdAndLearningResourceId(UUID personId,
            UUID learningResourceId);

    /**
     * Find learning records by filters.
     * @param id ids
     * @param hasExtension extension keys
     * @param extensionPath jsonpath queries
     * @param extensionPathMatch jsonpath predicate queries
     * @param learningResourceId learning resource IDs
     * @param recordStatus record status
     * @return learning records
     */
    @Query(
            name = "LearningRecord.findLearningRecordsWithFilters",
            nativeQuery = true)
    List<LearningRecord> findLearningRecordsWithFilters(
            @Param("id") UUID[] id,
            @Param("hasExtension") String[] hasExtension,
            @Param("extensionPath") String[] extensionPath,
            @Param("extensionPathMatch") String[] extensionPathMatch,
            @Param("learningResourceId") UUID[] learningResourceId,
            @Param("recordStatus") String[] recordStatus);;

    /**
     * Convenience overload.
     * @param filter filter
     * @return learning records
     */
    default List<LearningRecord> findLearningRecordsWithFilters(
                    final LearningRecord.Filter filter) {
            return findLearningRecordsWithFilters(
                    filter.getId(),
                    filter.getHasExtension(),
                    filter.getExtensionPath(),
                    filter.getExtensionPathMatch(),
                    filter.getLearningResourceId(),
                    filter.getRecordStatus());
    }
}
