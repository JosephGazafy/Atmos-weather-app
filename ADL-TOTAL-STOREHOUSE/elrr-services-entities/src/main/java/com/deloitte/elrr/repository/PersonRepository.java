package com.deloitte.elrr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.Person;

@Repository
@SuppressWarnings("checkstyle:ParameterNumber")
public interface PersonRepository extends JpaRepository<Person, UUID> {

    /**
     * Find persons by optional filters.
     *
     * @param id Optional person ID filter
     * @param ifi Optional IFI (Inverse Functional Identifier) filter
     * @param associatedOrgId Optional associated organization ID filter
     * @param employerOrgId Optional employer organization ID filter
     * @param hasExtension Optional filter for extension keys
     * @param extensionPath Optional filter for JSONPath expressions
     * @param extensionPathMatch Optional filter for JSONPath predicates
     * @param name Optional name search filter
     * @param locationId Optional location ID filter for any location field
     * @param emailAddress Optional email address filter
     * @param phoneNumber Optional phone number filter (normalized search)
     * @param competencyId Optional competency ID filter
     * @param credentialId Optional credential ID filter
     * @param learningResourceId Optional learning resource ID filter
     * @return List of persons matching the criteria
     */
    List<Person> findPersonsWithFilters(
            @Param("id") UUID[] id,
            @Param("ifi") String[] ifi,
            @Param("associatedOrgId") UUID[] associatedOrgId,
            @Param("employerOrgId") UUID[] employerOrgId,
            @Param("hasExtension") String[] hasExtension,
            @Param("extensionPath") String[] extensionPath,
            @Param("extensionPathMatch") String[] extensionPathMatch,
            @Param("name") String[] name,
            @Param("locationId") UUID[] locationId,
            @Param("emailAddress") String[] emailAddress,
            @Param("phoneNumber") String[] phoneNumber,
            @Param("competencyId") UUID[] competencyId,
            @Param("credentialId") UUID[] credentialId,
            @Param("learningResourceId") String[] learningResourceId);

    /**
     * Find persons using a filter object.
     *
     * @param filter Person.Filter containing all filter criteria
     * @return List of persons matching the criteria
     */
    default List<Person> findPersonsWithFilters(Person.Filter filter) {
        return findPersonsWithFilters(
            filter.getId(),
            filter.getIfi(),
            filter.getAssociatedOrgId(),
            filter.getEmployerOrgId(),
            filter.getHasExtension(),
            filter.getExtensionPath(),
            filter.getExtensionPathMatch(),
            filter.getName(),
            filter.getLocationId(),
            filter.getEmailAddress(),
            filter.getPhoneNumber(),
            filter.getCompetencyId(),
            filter.getCredentialId(),
            filter.getLearningResourceId()
        );
    }
}
