package com.deloitte.elrr.jpa.svc;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.repository.CompetencyRepository;

@Service
public class CompetencySvc implements CommonSvc<Competency, UUID> {

    private final CompetencyRepository competencyRepository;

    /**
     *
     * @param argsCompetencyRepository
     */
    public CompetencySvc(final CompetencyRepository argsCompetencyRepository) {
        this.competencyRepository = argsCompetencyRepository;
    }

    @Override
    public CrudRepository<Competency, UUID> getRepository() {
        return this.competencyRepository;
    }

    @Override
    public UUID getId(final Competency competency) {
        return competency.getId();
    }

    @Override
    public Competency save(final Competency competency) {
        return CommonSvc.super.save(competency);
    }

    /**
     * @param identifier
     * @return competency
     */
    public Competency findByIdentifier(String identifier) {
        Competency competency = competencyRepository.findByIdentifier(
                identifier);
        return competency;
    }

    /**
     * Find competencies with optional filters using a Competency.Filter object.
     *
     * @param filter Competency.Filter containing all filter criteria
     * @return List of competencies matching the criteria
     */
    public List<Competency> findCompetenciesWithFilters(
        Competency.Filter filter) {
        return competencyRepository.findCompetenciesWithFilters(filter);
    }

}
