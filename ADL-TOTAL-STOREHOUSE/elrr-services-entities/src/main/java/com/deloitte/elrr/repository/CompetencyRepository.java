package com.deloitte.elrr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.Competency;

@Repository
public interface CompetencyRepository extends JpaRepository<Competency, UUID> {

    /**
     * @param identifier
     * @return Competency
     */
    Competency findByIdentifier(String identifier);

    /**
     * Find competencies by optional filters.
     *
     * @param id Optional competency ID filter
     * @param hasExtension Optional filter for extension keys
     * @param extensionPath Optional filter for JSONPath expressions
     * @param extensionPathMatch Optional filter for JSONPath predicates
     * @param identifier Optional filter for competency identifier
     * @param identifierUrl Optional filter for competency identifier URL
     * @param code Optional filter for competency code
     * @return List of competencies matching the criteria
     */
    List<Competency> findCompetenciesWithFilters(
        @Param("id") UUID[] id,
        @Param("hasExtension") String[] hasExtension,
        @Param("extensionPath") String[] extensionPath,
        @Param("extensionPathMatch") String[] extensionPathMatch,
        @Param("identifier") String[] identifier,
        @Param("identifierUrl") String[] identifierUrl,
        @Param("code") String[] code
    );

    /**
     * Find competencies using a filter object.
     *
     * @param filter Competency.Filter containing all filter criteria
     * @return List of competencies matching the criteria
     */
    default List<Competency> findCompetenciesWithFilters(
        Competency.Filter filter) {
        return findCompetenciesWithFilters(
            filter.getId(),
            filter.getHasExtension(),
            filter.getExtensionPath(),
            filter.getExtensionPathMatch(),
            filter.getIdentifier(),
            filter.getIdentifierUrl(),
            filter.getCode()
        );
    }
}
