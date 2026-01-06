package com.deloitte.elrr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.LearningResource;

@Repository
public interface LearningResourceRepository extends JpaRepository<
        LearningResource, UUID> {

    /**
     * @param iri
     * @return LearningResource
     */
    LearningResource findByIri(String iri);

    /**
     * Find learning resources by optional filters.
     * @param id ids
     * @param hasExtension extension keys
     * @param extensionPath jsonpath queries
     * @param extensionPathMatch jsonpath predicate queries
     * @param iri IRIs
     * @param title Titles
     * @param subjectMatter Subject Matters
     * @return learning resources
     */
    List<LearningResource> findLearningResourcesWithFilters(
        @Param("id") UUID[] id,
        @Param("hasExtension") String[] hasExtension,
        @Param("extensionPath") String[] extensionPath,
        @Param("extensionPathMatch") String[] extensionPathMatch,
        @Param("iri") String[] iri,
        @Param("title") String[] title,
        @Param("subjectMatter") String[] subjectMatter);
    /**
     * Convenience overload using filter object.
     * @param filter filter
     * @return learning resources
     */
    default List<LearningResource> findLearningResourcesWithFilters(
        final LearningResource.Filter filter) {
    return findLearningResourcesWithFilters(
        filter.getId(),
        filter.getHasExtension(),
        filter.getExtensionPath(),
        filter.getExtensionPathMatch(),
        filter.getIri(),
        filter.getTitle(),
        filter.getSubjectMatter());
    }
}
