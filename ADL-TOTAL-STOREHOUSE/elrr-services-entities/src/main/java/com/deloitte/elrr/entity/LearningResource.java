package com.deloitte.elrr.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.NamedNativeQuery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "learning_resource")
@NamedNativeQuery(
    name = "LearningResource.findLearningResourcesWithFilters",
    query =
    """
    SELECT DISTINCT lr.* FROM {h-schema}learning_resource lr
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
    -- by iri
    AND (CAST(:iri AS text[]) IS NULL OR lr.iri = ANY(:iri))
    -- by title
    AND (CAST(:title AS text[]) IS NULL OR lr.title ILIKE ANY(:title))
    -- by subject matter
    AND (CAST(:subjectMatter AS text[]) IS NULL OR
        lr.subject_matter ILIKE ANY(:subjectMatter))
    """,
    resultClass = LearningResource.class
)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LearningResource extends Extensible {

    @Column(name = "iri")
    private String iri;

    @Column(name = "title")
    private String title;

    @Column(name = "subject_matter")
    private String subjectMatter;

    @Column(name = "subject_abbreviation", length = 20)
    private String subjectAbbreviation;

    @Column(name = "level")
    private String level;

    @Column(name = "number")
    private String number;

    @Column(name = "instruction_method")
    private String instructionMethod;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "provider_name")
    private String providerName;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "grade_scale_code")
    private String gradeScaleCode;

    @Column(name = "metadata_repository")
    private String metadataRepository;

    @Column(name = "lrs_endpoint")
    private String lrsEndpoint;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Override
    public String toString() {
        return "LearningResource [iri=" + iri + ", title=" + title + ", id="
                + id + ", subjectMatter=" + subjectMatter
                + ", subjectAbbreviation=" + subjectAbbreviation + ", level="
                + level + ", number=" + number + ", instructionMethod="
                + instructionMethod + ", startDate=" + startDate + ", endDate="
                + endDate + ", providername = " + providerName
                + ", departmentname = " + departmentName + ", gradeScaleCode="
                + gradeScaleCode + ", metadataRepository=" + metadataRepository
                + ", lrsEndpoint=" + lrsEndpoint + ", description="
                + description + "]";
    }

    /**
     * Filter object for LearningResource queries.
     */
    @Getter
    @Setter
    public static class Filter extends Extensible.Filter {
        /**
         * Filter by specific IRIs.
         */
        private String[] iri;

        /**
         * Search filter by title.
         */
        private String[] title;

        /**
         * Search filter by subject matter.
         */
        private String[] subjectMatter;
    }
}
