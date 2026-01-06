package com.deloitte.elrr.jpa.svc;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.LearningResource;
import com.deloitte.elrr.repository.LearningResourceRepository;

@Service
public class LearningResourceSvc implements CommonSvc<LearningResource, UUID> {

    private final LearningResourceRepository learningResourceRepository;

    /**
     *
     * @param argsLearningResourceRepository
     */
    public LearningResourceSvc(
            final LearningResourceRepository argsLearningResourceRepository) {
        this.learningResourceRepository = argsLearningResourceRepository;
    }

    @Override
    public CrudRepository<LearningResource, UUID> getRepository() {
        return this.learningResourceRepository;
    }

    @Override
    public UUID getId(final LearningResource learningResource) {
        return learningResource.getId();
    }

    @Override
    public LearningResource save(final LearningResource learningResource) {
        return CommonSvc.super.save(learningResource);
    }

    /**
     * @param iri
     * @return learningResource
     */
    public LearningResource findByIri(String iri) {
        LearningResource learningResource = learningResourceRepository
                .findByIri(iri);
        return learningResource;
    }

    /**
     * Filter search.
     * @param filter filter
     * @return learning resources
     */
    public java.util.List<LearningResource> findLearningResourcesWithFilters(
            final LearningResource.Filter filter) {
        return learningResourceRepository
                .findLearningResourcesWithFilters(filter);
    }
}
