package com.deloitte.elrr.entity;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import com.deloitte.elrr.entity.types.GoalType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * A Goal represents a relationship between a Person and a number of
 * Competencies, Credentials, and Learning Resources.
 */

 @Entity
 @Table(name = "goal")
 @NamedNativeQuery(
    name = "Goal.findGoalsWithFilters",
    query =
    """
    SELECT DISTINCT g.* FROM {h-schema}goal g
    -- by ID
    WHERE (CAST(:id AS uuid[]) IS NULL OR g.id = ANY(:id))
    -- by presence of (all) extension keys
    AND (CAST(:hasExtension AS text[]) IS NULL OR
        g.extensions \\?\\?& CAST(:hasExtension AS text[]))
    -- by returning items from all jsonpath queries
    AND (CAST(:extensionPath AS text[]) IS NULL OR
        (SELECT bool_and(g.extensions @\\?\\? path::jsonpath)
         FROM unnest(CAST(:extensionPath AS text[])) AS path))
    -- by returning items from all jsonpath predicates
    AND (CAST(:extensionPathMatch AS text[]) IS NULL OR
        (SELECT bool_and(g.extensions @@ path::jsonpath)
         FROM unnest(CAST(:extensionPathMatch AS text[])) AS path))
    """,
    resultClass = Goal.class
 )
 @RequiredArgsConstructor
 @AllArgsConstructor
 @Getter
 @Setter
 public class Goal extends Extensible {

    /**
     * The Person who owns the Goal.
     */
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    /**
     * The id of the Goal.
     */
    @Column(name = "goal_id", nullable = false)
    private String goalId;

    /**
     * The type of the Goal, enum of SELF or ASSIGNED.
     */
    @Column(name = "type", nullable = false,
            columnDefinition = "elrr.goal_type")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private GoalType type;

    /**
     * The name of the Goal.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The description of the Goal.
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * The start date of the Goal.
     */
    @Column(name = "start_date")
    private ZonedDateTime startDate;

    /**
     * The achieved-by date of the Goal.
     */
    @Column(name = "achieved_by_date")
    private ZonedDateTime achievedByDate;

    /**
     * The expiration date of the Goal.
     */
    @Column(name = "expiration_date")
    private ZonedDateTime expirationDate;

    /**
     * The Competencies associated with the Goal.
     */
    @ManyToMany
    @JoinTable(name = "goal_competency", joinColumns = @JoinColumn(
            name = "goal_id"), inverseJoinColumns = @JoinColumn(
                    name = "qualification_id"))
    private Set<Competency> competencies;

    /**
     * The Credentials associated with the Goal.
     */
    @ManyToMany
    @JoinTable(name = "goal_credential", joinColumns = @JoinColumn(
            name = "goal_id"), inverseJoinColumns = @JoinColumn(
                    name = "qualification_id"))
    private Set<Credential> credentials;

    /**
     * The Learning Resources associated with the Goal.
     */
    @ManyToMany
    @JoinTable(name = "goal_learning_resource", joinColumns = @JoinColumn(
            name = "goal_id"), inverseJoinColumns = @JoinColumn(
                    name = "learning_resource_id"))
    private Set<LearningResource> learningResources;

    @Override
    public String toString() {
        return "Goal [id=" + id + ", person=" + person + "]";
    }

    /**
     * Get the IDs of any competencies associated with this Goal.
     *
     * @return a Set of UUIDs representing the IDs of the competencies
     */
    @Transient
    public Set<UUID> getCompetencyIds() {
        Set<UUID> competencyIds = new HashSet<>();
        if (competencies != null) {
            competencies.forEach(competency -> {
                competencyIds.add(competency.getId());
            });
        }
        return competencyIds;
    }

    /**
     * Get the IDs of any credentials associated with this Goal.
     *
     * @return a Set of UUIDs representing the IDs of the credentials
     */
    @Transient
    public Set<UUID> getCredentialIds() {
        Set<UUID> credentialIds = new HashSet<>();
        if (credentials != null) {
            credentials.forEach(credential -> {
                credentialIds.add(credential.getId());
            });
        }
        return credentialIds;
    }

    /**
     * Get the IDs of any learning resources associated with this Goal.
     *
     * @return a Set of UUIDs representing the IDs of the learning resources
     */
    @Transient
    public Set<UUID> getLearningResourceIds() {
        Set<UUID> learningResourceIds = new HashSet<>();
        if (learningResources != null) {
            learningResources.forEach(learningResource -> {
                learningResourceIds.add(learningResource.getId());
            });
        }
        return learningResourceIds;
    }

    /**
     * Filter object for Goal queries (id + extension filters).
     */
    @Getter
    @Setter
    public static class Filter extends Extensible.Filter {
    }
}
