package com.deloitte.elrr.entity;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.deloitte.elrr.util.ValueObjectTestUtility;

class GoalTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /**
    *
    */
    @Test
    void test() {
        ValueObjectTestUtility.validateAccessors(Goal.class);
    }

    /**
     *
     */
    @Test
    void testToString() {
        assertNotNull(new Goal().toString());
    }

    /**
     *
     */
    @Test
    void testGetCompetencyIds() {
        UUID competencyId = UUID.randomUUID();
        Competency competency = new Competency();
        competency.setId(competencyId);
        Set<Competency> competencies = Set.of(competency);
        Goal goal = new Goal();
        goal.setCompetencies(competencies);
        Set<UUID> competencyIds = goal.getCompetencyIds();
        assertNotNull(competencyIds.iterator().next().equals(competencyId));
    }
    /**
     *
     */
    @Test
    void testGetCredentialIds() {
        UUID credentialId = UUID.randomUUID();
        Credential credential = new Credential();
        credential.setId(credentialId);
        Set<Credential> credentials = Set.of(credential);
        Goal goal = new Goal();
        goal.setCredentials(credentials);
        Set<UUID> credentialIds = goal.getCredentialIds();
        assertNotNull(credentialIds.iterator().next().equals(credentialId));
    }
    /**
     *
     */
    @Test
    void testGetLearningResourceIds() {
        UUID learningResourceId = UUID.randomUUID();
        LearningResource learningResource = new LearningResource();
        learningResource.setId(learningResourceId);
        Set<LearningResource> learningResources = Set.of(learningResource);
        Goal goal = new Goal();
        goal.setLearningResources(learningResources);
        Set<UUID> learningResourceIds = goal.getLearningResourceIds();
        assertNotNull(learningResourceIds.iterator().next().equals(learningResourceId));
    }
}
