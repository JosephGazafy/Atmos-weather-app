package com.deloitte.elrr.entity;

import java.util.UUID;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.NamedNativeQuery;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@NamedNativeQuery(
    name = "Person.findPersonsWithFilters",
    query =
    """
    SELECT DISTINCT p.* FROM {h-schema}person p
    LEFT JOIN {h-schema}identity i ON p.id = i.person_id
    LEFT JOIN {h-schema}association a ON p.id = a.person_id
    LEFT JOIN {h-schema}employment_record er ON p.id = er.employee
    LEFT JOIN {h-schema}organization org_assoc
        ON a.organization_id = org_assoc.id
    LEFT JOIN {h-schema}organization org_emp
        ON er.employer_organization = org_emp.id
    LEFT JOIN {h-schema}person_email pe ON p.id = pe.person_id
    LEFT JOIN {h-schema}email e ON pe.email_id = e.id
    LEFT JOIN {h-schema}person_phone pp ON p.id = pp.person_id
    LEFT JOIN {h-schema}phone ph ON pp.phone_id = ph.id
    -- by ID
    WHERE (CAST(:id AS uuid[]) IS NULL OR p.id = ANY(:id))
    -- by IFI
    AND (CAST(:ifi AS text[]) IS NULL OR i.ifi = ANY(:ifi))
    -- by associated organization
    AND (CAST(:associatedOrgId AS uuid[]) IS NULL OR
        org_assoc.id = ANY(:associatedOrgId))
    -- by employer organization
    AND (CAST(:employerOrgId AS uuid[]) IS NULL OR
        org_emp.id = ANY(:employerOrgId))
    -- by presence of (all) extension keys
    AND (CAST(:hasExtension AS text[]) IS NULL OR
        p.extensions \\?\\?& CAST(:hasExtension AS text[]))
    -- by returning items from all jsonpath queries
    AND (CAST(:extensionPath AS text[]) IS NULL OR
        (SELECT bool_and(p.extensions @\\?\\? path::jsonpath)
         FROM unnest(CAST(:extensionPath AS text[])) AS path))
    -- by returning items from all jsonpath predicates
    AND (CAST(:extensionPathMatch AS text[]) IS NULL OR
        (SELECT bool_and(p.extensions @@ path::jsonpath)
         FROM unnest(CAST(:extensionPathMatch AS text[])) AS path))
    -- fuzzy search filter by name
    AND (CAST(:name AS text[]) IS NULL OR
        (p.name ILIKE ANY(CAST(:name AS text[]))
         OR p.first_name ILIKE ANY(CAST(:name AS text[]))
         OR p.middle_name ILIKE ANY(CAST(:name AS text[]))
         OR p.last_name ILIKE ANY(CAST(:name AS text[]))))
    -- by location ID in any location field
    AND (CAST(:locationId AS uuid[]) IS NULL OR
        (p.mailing_address_id = ANY(:locationId)
         OR p.physical_address_id = ANY(:locationId)
         OR p.shipping_address_id = ANY(:locationId)
         OR p.billing_address_id = ANY(:locationId)
         OR p.on_campus_address_id = ANY(:locationId)
         OR p.off_campus_address_id = ANY(:locationId)
         OR p.temporary_address_id = ANY(:locationId)
         OR p.permanent_student_address_id = ANY(:locationId)
         OR p.employment_address_id = ANY(:locationId)
         OR p.time_of_admission_address_id = ANY(:locationId)
         OR p.father_address_id = ANY(:locationId)
         OR p.mother_address_id = ANY(:locationId)
         OR p.guardian_address_id = ANY(:locationId)
         OR p.birthplace_id = ANY(:locationId)))
    -- by email address
    AND (CAST(:emailAddress AS text[]) IS NULL OR
        e.email_address ILIKE ANY(CAST(:emailAddress AS text[])))
    -- by phone number (normalized - removes formatting characters)
    AND (CAST(:phoneNumber AS text[]) IS NULL OR
        (SELECT bool_or(
            REGEXP_REPLACE(ph.telephone_number, '[^0-9]', '', 'g') =
            REGEXP_REPLACE(phone_filter, '[^0-9]', '', 'g')
        ) FROM unnest(CAST(:phoneNumber AS text[])) AS phone_filter))
    -- by competency ID
    AND (CAST(:competencyId AS uuid[]) IS NULL OR
        EXISTS (SELECT 1 FROM {h-schema}person_qualification pq
                WHERE pq.person_id = p.id
                AND pq.type = 'COMPETENCY'
                AND pq.qualification_id = ANY(:competencyId)))
    -- by credential ID
    AND (CAST(:credentialId AS uuid[]) IS NULL OR
        EXISTS (SELECT 1 FROM {h-schema}person_qualification pq
                WHERE pq.person_id = p.id
                AND pq.type = 'CREDENTIAL'
                AND pq.qualification_id = ANY(:credentialId)))
    -- by learning resource ID
    AND (CAST(:learningResourceId AS text[]) IS NULL OR
        EXISTS (SELECT 1 FROM {h-schema}learning_record lr
                WHERE lr.person_id = p.id
                AND lr.learning_resource_id::text = ANY(:learningResourceId)))
    """,
    resultClass = Person.class
)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person extends Extensible {

    @Column(name = "name")
    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "name_prefix")
    private String namePrefix;

    @Column(name = "title_affix_code")
    private String titleAffixCode;

    @Column(name = "name_suffix")
    private String nameSuffix;

    @Column(name = "qualification_affix_code")
    private String qualificationAffixCode;

    @Column(name = "maiden_name")
    private String maidenName;

    @ManyToOne
    @JoinColumn(name = "mailing_address_id")
    private Location mailingAddress;

    @ManyToOne
    @JoinColumn(name = "physical_address_id")
    private Location physicalAddress;

    @ManyToOne
    @JoinColumn(name = "shipping_address_id")
    private Location shippingAddress;

    @ManyToOne
    @JoinColumn(name = "billing_address_id")
    private Location billingAddress;

    @ManyToOne
    @JoinColumn(name = "on_campus_address_id")
    private Location onCampusAddress;

    @ManyToOne
    @JoinColumn(name = "off_campus_address_id")
    private Location offCampusAddress;

    @ManyToOne
    @JoinColumn(name = "temporary_address_id")
    private Location temporaryAddress;

    @ManyToOne
    @JoinColumn(name = "permanent_student_address_id")
    private Location permanentStudentAddress;

    @ManyToOne
    @JoinColumn(name = "employment_address_id")
    private Location employmentAddress;

    @ManyToOne
    @JoinColumn(name = "time_of_admission_address_id")
    private Location timeOfAdmissionAddress;

    @ManyToOne
    @JoinColumn(name = "father_address_id")
    private Location fatherAddress;

    @ManyToOne
    @JoinColumn(name = "mother_address_id")
    private Location motherAddress;

    @ManyToOne
    @JoinColumn(name = "guardian_address_id")
    private Location guardianAddress;

    @ManyToOne
    @JoinColumn(name = "birthplace_id")
    private Location birthplaceAddress;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @ManyToMany
    @JoinTable(name = "person_phone",
        joinColumns = @JoinColumn(name = "person_id",
            referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "phone_id",
            referencedColumnName = "id"))
    private Set<Phone> phoneNumbers;

    @ManyToMany
    @JoinTable(name = "person_email",
        joinColumns = @JoinColumn(name = "person_id",
            referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "email_id",
            referencedColumnName = "id"))
    private Set<Email> emailAddresses;

    @OneToMany(mappedBy = "person")
    private Set<PersonalCompetency> competencies;

    @OneToMany(mappedBy = "person")
    private Set<PersonalCredential> credentials;

    @OneToMany(mappedBy = "person")
    private Set<LearningRecord> learningRecords;

    @OneToMany(mappedBy = "person")
    private Set<Association> associations;

    @OneToMany(mappedBy = "employee")
    private Set<EmploymentRecord> employmentRecords;

    @OneToMany(mappedBy = "person")
    private Set<Identity> identities;

    @Column(name = "citizenship")
    private String citizenship;

    @Column(name = "height", precision = 5, scale = 2)
    private BigDecimal height;

    @Column(name = "height_unit")
    private String heightUnit;

    @Column(name = "weight", precision = 5, scale = 2)
    private BigDecimal weight;

    @Column(name = "weight_unit")
    private String weightUnit;

    @Column(name = "interpupillary_distance")
    private Long interpupillaryDistance;

    @Column(name = "handedness")
    private String handedness;

    @Column(name = "primary_language")
    private String primaryLanguage;

    @Column(name = "current_security_clearance")
    private String currentSecurityClearance;

    @Column(name = "highest_security_clearance")
    private String highestSecurityClearance;

    @Column(name = "union_membership")
    private Boolean unionMembership;

    @Override
    public String toString() {
        return "Person [id=" + id + ", name = " + name + ", firstname = "
        + firstName + ", middlename = " + middleName + ", lastname = "
        + lastName + ", namePrefix=" + namePrefix + ", titleAffixCode="
        + titleAffixCode + ", nameSuffix=" + nameSuffix
        + ", qualificationAffixCode=" + qualificationAffixCode
        + ", maidenname = " + maidenName + ", mailingAddress=" + mailingAddress
        + ", phoneNumbers=" + phoneNumbers + ", emailAddresses="
        + emailAddresses + ", competencies=" + competencies + ", credentials="
        + credentials + ", learningRecords=" + learningRecords
        + ", associations=" + associations + ", employmentRecords="
        + employmentRecords + ", identities=" + identities + ", citizenship="
        + citizenship + ", height=" + height + ", heightUnit=" + heightUnit
        + ", weight=" + weight + ", weightUnit=" + weightUnit
        + ", interpupillaryDistance=" + interpupillaryDistance + ", handedness="
        + handedness + ", primaryLanguage=" + primaryLanguage
        + ", currentSecurityClearance=" + currentSecurityClearance
        + ", highestSecurityClearance=" + highestSecurityClearance + "]";
    }

    /**
     * Filter criteria for searching persons.
     * All fields are optional and correspond to query parameters.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Filter extends Extensible.Filter {

        /**
         * Optional IFI (Inverse Functional Identifier) filter.
         */
        private String[] ifi;

        /**
         * Optional associated organization ID filter.
         */
        private UUID[] associatedOrgId;

        /**
         * Optional employer organization ID filter.
         */
        private UUID[] employerOrgId;

        /**
         * Optional filter for person names.
         */
        private String[] name;

        /**
         * Optional location ID filter for any location field.
         */
        private UUID[] locationId;

        /**
         * Optional filter for email addresses.
         */
        private String[] emailAddress;

        /**
         * Optional filter for phone numbers (normalized search).
         */
        private String[] phoneNumber;

        /**
         * Optional competency ID filter.
         */
        private UUID[] competencyId;

        /**
         * Optional credential ID filter.
         */
        private UUID[] credentialId;

        /**
         * Optional learning resource ID filter.
         */
        private String[] learningResourceId;
    }
}
