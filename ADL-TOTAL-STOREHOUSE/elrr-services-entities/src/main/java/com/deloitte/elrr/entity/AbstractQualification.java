package com.deloitte.elrr.entity;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "qualification")
@DiscriminatorColumn(name = "type")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AbstractQualification extends Extensible {

    @Column(name = "identifier", length = 100)
    private String identifier;

    @Column(name = "identifier_url", columnDefinition = "TEXT")
    private String identifierUrl;

    @Column(name = "code", length = 100)
    private String code;

    @Column(name = "taxonomy_id", length = 100)
    private String taxonomyId;

    @Column(name = "valid_start_date")
    private ZonedDateTime validStartDate;

    @Column(name = "valid_end_date")
    private ZonedDateTime validEndDate;

    @Column(name = "parent_id", length = 100)
    private String parentId;

    @Column(name = "parent_url", columnDefinition = "TEXT")
    private String parentUrl;

    @Column(name = "parent_code", length = 100)
    private String parentCode;

    @Column(name = "type_url", columnDefinition = "TEXT")
    private String typeUrl;

    @Column(name = "statement", columnDefinition = "TEXT")
    private String statement;

    @Column(name = "framework_title", length = 100)
    private String frameworkTitle;

    @Column(name = "framework_version", length = 100)
    private String frameworkVersion;

    @Column(name = "framework_identifier", length = 100)
    private String frameworkIdentifier;

    @Column(name = "framework_description", columnDefinition = "TEXT")
    private String frameworkDescription;

    @Column(name = "framework_subject", length = 100)
    private String frameworkSubject;

    @Column(name = "framework_valid_start_date")
    private LocalDate frameworkValidStartDate;

    @Column(name = "framework_valid_end_date")
    private LocalDate frameworkValidEndDate;

    @Column(name = "record_status", length = 10)
    private String recordStatus;

    @Override
    @SuppressWarnings("checkstyle:linelength")
    public String toString() {
        return "AbstractQualification [id=" + id + ", identifier=" + identifier
                + ", identifierUrl=" + identifierUrl + ", code=" + code
                + ", taxonomyId=" + taxonomyId + ", validStartDate="
                + validStartDate + ", validEndDate=" + validEndDate
                + ", parentId=" + parentId + ", parentUrl=" + parentUrl
                + ", parentCode=" + parentCode + ", typeUrl=" + typeUrl
                + ", statement=" + statement + ", frameworkTitle="
                + frameworkTitle + ", frameworkVersion=" + frameworkVersion
                + ", frameworkIdentifier=" + frameworkIdentifier
                + ", frameworkDescription=" + frameworkDescription
                + ", frameworkSubject=" + frameworkSubject
                + ", frameworkValidStartDate=" + frameworkValidStartDate
                + ", frameworkValidEndDate=" + frameworkValidEndDate
                + ", recordStatus=" + recordStatus + "]";
    }

    @Getter
    @Setter
    public static class Filter extends Extensible.Filter {
        /**
         * Optional Identifier search filter.
         */
        private String[] identifier;
        /**
         * Optional identifierUrl filter.
         */
        private String[] identifierUrl;
        /**
         * Optional code filter.
         */
        private String[] code;
    }

}
