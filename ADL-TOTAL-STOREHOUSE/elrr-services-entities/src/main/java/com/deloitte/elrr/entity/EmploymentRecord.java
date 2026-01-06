package com.deloitte.elrr.entity;


import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employment_record")
@NamedNativeQuery(
    name = "EmploymentRecord.findEmploymentRecordsWithFilters",
    query =
    """
    SELECT DISTINCT er.* FROM {h-schema}employment_record er
    -- by ID
    WHERE (CAST(:id AS uuid[]) IS NULL OR er.id = ANY(:id))
    -- by presence of (all) extension keys
    AND (CAST(:hasExtension AS text[]) IS NULL OR
        er.extensions \\?\\?& CAST(:hasExtension AS text[]))
    -- by returning items from all jsonpath queries
    AND (CAST(:extensionPath AS text[]) IS NULL OR
        (SELECT bool_and(er.extensions @\\?\\? path::jsonpath)
         FROM unnest(CAST(:extensionPath AS text[])) AS path))
    -- by returning items from all jsonpath predicates
    AND (CAST(:extensionPathMatch AS text[]) IS NULL OR
        (SELECT bool_and(er.extensions @@ path::jsonpath)
         FROM unnest(CAST(:extensionPathMatch AS text[])) AS path))
    -- by employer organization ID
    AND (CAST(:employerOrgId AS uuid[]) IS NULL OR
        er.employer_organization = ANY(:employerOrgId))
    -- by position
    AND (CAST(:position AS text[]) IS NULL OR
        er.position ILIKE ANY(:position))
    -- by position title
    AND (CAST(:positionTitle AS text[]) IS NULL OR
        er.position_title ILIKE ANY(:positionTitle))
    -- by position description
    AND (CAST(:positionDescription AS text[]) IS NULL OR
        er.position_description ILIKE ANY(:positionDescription))
    """,
    resultClass = EmploymentRecord.class
)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmploymentRecord extends Extensible {

    @ManyToOne
    @JoinColumn(name = "employer_organization")
    private Organization employerOrganization;

    @ManyToOne
    @JoinColumn(name = "employee")
    private Person employee;

    @Column(name = "custom_employment_record_id")
    private String customEmploymentRecordId;

    @Column(name = "employee_type")
    private String employeeType;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "hire_type")
    private String hireType;

    @Column(name = "employment_start_date")
    private LocalDate employmentStartDate;

    @Column(name = "employment_end_date")
    private LocalDate employmentEndDate;

    @Column(name = "position")
    private String position;

    @Column(name = "position_title")
    private String positionTitle;

    @Column(name = "position_description", columnDefinition = "TEXT")
    private String positionDescription;

    @Column(name = "job_level")
    private String jobLevel;

    @Column(name = "occupation")
    private String occupation;

    @ManyToOne
    @JoinColumn(name = "employment_location")
    private Location employmentLocation;

    @ManyToOne
    @JoinColumn(name = "employment_facility")
    private Facility employmentFacility;

    @ManyToMany
    @JoinTable(
            name = "employment_qualification",
            joinColumns = @JoinColumn(name = "employment_record_id"),
            inverseJoinColumns = @JoinColumn(name = "qualification_id"))
    private Set<Credential> credentials;

    @ManyToMany
    @JoinTable(
            name = "employment_qualification",
            joinColumns = @JoinColumn(name = "employment_record_id"),
            inverseJoinColumns = @JoinColumn(name = "qualification_id"))
    private Set<Competency> competencies;

    @Override
    public String toString() {
        return "EmploymentRecord [id=" + id + ", employerOrganization="
        + employerOrganization + ", employee=" + employee
        + ", customEmploymentRecordId=" + customEmploymentRecordId
        + ", employeeType=" + employeeType + ", hireDate=" + hireDate
        + ", hireType=" + hireType + ", employmentStartDate="
        + employmentStartDate + ", employmentEndDate=" + employmentEndDate
        + ", position=" + position + ", positionTitle=" + positionTitle
        + ", positionDescription=" + positionDescription + ", jobLevel="
        + jobLevel + ", occupation=" + occupation + ", employmentLocation="
        + employmentLocation + ", employmentFacility=" + employmentFacility
        + ", credentials=" + credentials + ", competencies=" + competencies
        + "]";
    }

    @Getter
    @Setter
    public static class Filter extends Extensible.Filter {
        /**
         * Optional Employer Organization filter.
         */
        private UUID[] employerOrgId;

        /**
         * Optional position filter.
         */
        private String[] position;

        /**
         * Optional position title filter.
         */
        private String[] positionTitle;

        /**
         * Optional position description filter.
         */
        private String[] positionDescription;
    }

}
