package com.deloitte.elrr.entity;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import com.deloitte.elrr.entity.types.LearningStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "learning_record")
@NamedNativeQuery(
    name = "LearningRecord.findLearningRecordsWithFilters",
    query =
    """
    SELECT DISTINCT lr.* FROM {h-schema}learning_record lr
    -- by ID
    WHERE (CAST(:id AS uuid[]) IS NULL OR lr.id = ANY(:id))
    -- by presence of (all) extension keys
    AND (CAST(:hasExtension AS text[]) IS NULL OR
        lr.extensions \\?\\?& CAST(:hasExtension AS text[]))
    -- by returning items from all jsonpath queries
    AND (CAST(:extensionPath AS text[]) IS NULL OR
        (SELECT bool_and(lr.extensions @\\?\\? path::jsonpath)
         FROM unnest(CAST(:extensionPath AS text[])) AS path))
    -- by returning items from all jsonpath predicates
    AND (CAST(:extensionPathMatch AS text[]) IS NULL OR
        (SELECT bool_and(lr.extensions @@ path::jsonpath)
         FROM unnest(CAST(:extensionPathMatch AS text[])) AS path))
    -- by learning resource id
    AND (CAST(:learningResourceId AS uuid[]) IS NULL OR
        lr.learning_resource_id = ANY(:learningResourceId))
    -- by record status
    AND (CAST(:recordStatus AS text[]) IS NULL OR
        lr.record_status::text = ANY(:recordStatus))
    """,
    resultClass = LearningRecord.class
)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LearningRecord extends Extensible {

    @ManyToOne
    @JoinColumn(name = "learning_resource_id")
    private LearningResource learningResource;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "enrollment_date")
    private ZonedDateTime enrollmentDate;

    @Column(name = "record_status", columnDefinition = "learning_status")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private LearningStatus recordStatus;

    @Column(name = "academic_grade", length = 50)
    private String academicGrade;

    @Column(name = "event_time")
    private ZonedDateTime eventTime;

    @Override
    public String toString() {
        return "LearningRecord [id=" + id + ", learningResource="
                + learningResource + ", person=" + person + ", enrollmentDate="
                + enrollmentDate + ", recordStatus=" + recordStatus
                + ", academicGrade=" + academicGrade + ", eventTime="
                + eventTime + "]";
    }

    /**
     * Filter object for LearningRecord queries (id + extension filters).
     */
    @Getter
    @Setter
    public static class Filter extends Extensible.Filter {
        /**
         * Optional filter for learning resource IDs.
         */
        private UUID[] learningResourceId;

        /**
         * Optional filter for record status.
         */
        private String[] recordStatus;

    }

}
