package com.deloitte.elrr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.Goal;

@Repository
public interface GoalRepository extends JpaRepository<Goal, UUID> {

    /**
     * Find goals by filters.
     *
     * @param id                 ids
     * @param hasExtension       extension keys
     * @param extensionPath      jsonpath queries
     * @param extensionPathMatch jsonpath predicate queries
     * @return goals
     */
    @Query(name = "Goal.findGoalsWithFilters", nativeQuery = true)
    List<Goal> findGoalsWithFilters(@Param("id") UUID[] id,
            @Param("hasExtension") String[] hasExtension,
            @Param("extensionPath") String[] extensionPath,
            @Param("extensionPathMatch") String[] extensionPathMatch);

    /**
     * Convenience overload.
     *
     * @param filter filter
     * @return goals
     */
    default List<Goal> findGoalsWithFilters(final Goal.Filter filter) {
        return findGoalsWithFilters(filter.getId(), filter.getHasExtension(),
                filter.getExtensionPath(), filter.getExtensionPathMatch());
    }

    /**
     * @param goalId
     * @return Goal
     */
    Goal findByGoalId(String goalId);

    /**
     * @param goalId
     * @return long
     */
    long deleteByGoalId(String goalId);
}
