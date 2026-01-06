package com.deloitte.elrr.jpa.svc;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.entity.Credential;
import com.deloitte.elrr.entity.Goal;
import com.deloitte.elrr.entity.Goal.Filter;
import com.deloitte.elrr.entity.LearningResource;
import com.deloitte.elrr.exception.RuntimeServiceException;
import com.deloitte.elrr.repository.GoalRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GoalSvc implements CommonSvc<Goal, UUID> {

    private final GoalRepository goalRepository;

    @Autowired
    private CompetencySvc competencySvc;

    @Autowired
    private CredentialSvc credentialSvc;

    @Autowired
    private LearningResourceSvc learningResourceSvc;

    /**
     * @param argsGoalRepository
     */
    public GoalSvc(final GoalRepository argsGoalRepository) {
        this.goalRepository = argsGoalRepository;
    }

    @Override
    public CrudRepository<Goal, UUID> getRepository() {
        return this.goalRepository;
    }

    @Override
    public UUID getId(final Goal goal) {
        return goal.getId();
    }

    @Override
    public Goal save(final Goal goal) {
        return CommonSvc.super.save(goal);
    }

    /**
     * Sets the competencies for the goal from an array of competency IDs.
     *
     * @param goal
     *            The goal to set competencies for.
     * @param competencyIds
     *            The array of competency IDs.
     * @return The updated goal with competencies set.
     */
    public Goal setCompetenciesFromIds(Goal goal, Set<UUID> competencyIds) {
        if (competencyIds != null) {
            Set<Competency> competencies = new HashSet<>();
            for (UUID competencyId : competencyIds) {
                Competency competency = competencySvc.get(competencyId)
                        .orElseThrow(() -> new RuntimeServiceException(
                                "Competency not found for id: "
                                        + competencyId));
                competencies.add(competency);
            }
            goal.setCompetencies(competencies);
        }
        return goal;
    }

    /**
     * Sets the credentials for the goal from an array of credential IDs.
     *
     * @param goal
     *            The goal to set credentials for.
     * @param credentialIds
     *            The array of credential IDs.
     * @return The updated goal with credentials set.
     */
    public Goal setCredentialsFromIds(Goal goal, Set<UUID> credentialIds) {
        if (credentialIds != null) {
            Set<Credential> credentials = new HashSet<>();
            for (UUID credentialId : credentialIds) {
                Credential credential = credentialSvc.get(credentialId)
                        .orElseThrow(() -> new RuntimeServiceException(
                                "Credential not found for id: "
                                        + credentialId));
                credentials.add(credential);
            }
            goal.setCredentials(credentials);
        }
        return goal;
    }

    /**
     * Sets the learning resources for the goal from an array of learning
     * resource IDs.
     *
     * @param goal
     *            The goal to set learning resources for.
     * @param learningResourceIds
     *            The array of learning resource IDs.
     * @return The updated goal with learning resources set.
     */
    public Goal setLearningResourcesFromIds(Goal goal,
            Set<UUID> learningResourceIds) {
        if (learningResourceIds != null) {
            Set<LearningResource> learningResources = new HashSet<>();
            for (UUID learningResourceId : learningResourceIds) {
                LearningResource learningResource = learningResourceSvc.get(
                        learningResourceId).orElseThrow(
                                () -> new RuntimeServiceException(
                                        "Learning Resource not found for id: "
                                                + learningResourceId));
                learningResources.add(learningResource);
            }
            goal.setLearningResources(learningResources);
        }
        return goal;
    }

    /**
     * Find Goals with filters.
     *
     * @param filter
     *            filter
     * @return list
     */
    public java.util.List<Goal> findGoalsWithFilters(final Filter filter) {
        return goalRepository.findGoalsWithFilters(filter);
    }

    /**
     * @param goalId
     * @return goal
     */
    public Goal findByGoalId(String goalId) {
        Goal goal = goalRepository.findByGoalId(goalId);
        return goal;
    }

    /**
     * @param goalId
     * @return rowsDeleted
     */
    public long deleteByGoalId(String goalId) {
        long rowsDeleted = goalRepository.deleteByGoalId(goalId);
        return rowsDeleted;
    }
}
