package com.deloitte.elrr.jpa.svc;

import java.util.UUID;
import java.util.HashSet;
import java.util.Set;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.deloitte.elrr.entity.Goal;
import com.deloitte.elrr.entity.types.GoalType;
import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.entity.Credential;
import com.deloitte.elrr.entity.LearningResource;
import com.deloitte.elrr.exception.RuntimeServiceException;
import com.deloitte.elrr.repository.GoalRepository;

@ExtendWith(MockitoExtension.class)
public class GoalSvcTest {

    @Mock
    private GoalRepository goalRepository;

    @Mock
    private CompetencySvc competencySvc;

    @Mock
    private CredentialSvc credentialSvc;

    @Mock
    private LearningResourceSvc learningResourceSvc;

    private GoalSvc goalSvc;

    private static final UUID GOAL_ID = UUID.randomUUID();
    private static final UUID COMPETENCY_ID_1 = UUID.randomUUID();
    private static final UUID COMPETENCY_ID_2 = UUID.randomUUID();
    private static final UUID CREDENTIAL_ID_1 = UUID.randomUUID();
    private static final UUID CREDENTIAL_ID_2 = UUID.randomUUID();
    private static final UUID LEARNING_RESOURCE_ID_1 = UUID.randomUUID();
    private static final UUID LEARNING_RESOURCE_ID_2 = UUID.randomUUID();

    private Goal goal;
    private Competency competency1;
    private Competency competency2;
    private Credential credential1;
    private Credential credential2;
    private LearningResource learningResource1;
    private LearningResource learningResource2;

    @BeforeEach
    void setUp() {
        // Manually create GoalSvc and inject mocks using reflection
        goalSvc = new GoalSvc(goalRepository);
        
        // Use reflection to inject the @Autowired fields
        try {
            java.lang.reflect.Field competencySvcField = GoalSvc.class.getDeclaredField("competencySvc");
            competencySvcField.setAccessible(true);
            competencySvcField.set(goalSvc, competencySvc);
            
            java.lang.reflect.Field credentialSvcField = GoalSvc.class.getDeclaredField("credentialSvc");
            credentialSvcField.setAccessible(true);
            credentialSvcField.set(goalSvc, credentialSvc);
            
            java.lang.reflect.Field learningResourceSvcField = GoalSvc.class.getDeclaredField("learningResourceSvc");
            learningResourceSvcField.setAccessible(true);
            learningResourceSvcField.set(goalSvc, learningResourceSvc);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mocks", e);
        }

        goal = new Goal();
        goal.setId(GOAL_ID);
        goal.setName("Test Goal");
        goal.setDescription("This is a test goal.");
        goal.setType(GoalType.SELF);

        competency1 = new Competency();
        competency1.setId(COMPETENCY_ID_1);

        competency2 = new Competency();
        competency2.setId(COMPETENCY_ID_2);

        credential1 = new Credential();
        credential1.setId(CREDENTIAL_ID_1);

        credential2 = new Credential();
        credential2.setId(CREDENTIAL_ID_2);

        learningResource1 = new LearningResource();
        learningResource1.setId(LEARNING_RESOURCE_ID_1);

        learningResource2 = new LearningResource();
        learningResource2.setId(LEARNING_RESOURCE_ID_2);
    }

    @Test
    void saveTest() {
        goalSvc.save(goal);
        // Basic save test - could be enhanced to verify repository interaction
    }

    @Test
    void testSetCompetenciesFromIds_Success() {
        // Arrange
        Set<UUID> competencyIds = Set.of(COMPETENCY_ID_1, COMPETENCY_ID_2);
        when(competencySvc.get(COMPETENCY_ID_1)).thenReturn(Optional.of(competency1));
        when(competencySvc.get(COMPETENCY_ID_2)).thenReturn(Optional.of(competency2));

        // Act
        Goal result = goalSvc.setCompetenciesFromIds(goal, competencyIds);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getCompetencies());
        assertEquals(2, result.getCompetencies().size());
        assertTrue(result.getCompetencies().contains(competency1));
        assertTrue(result.getCompetencies().contains(competency2));
        
        verify(competencySvc).get(COMPETENCY_ID_1);
        verify(competencySvc).get(COMPETENCY_ID_2);
    }

    @Test
    void testSetCompetenciesFromIds_WithNullIds() {
        // Act
        Goal result = goalSvc.setCompetenciesFromIds(goal, null);

        // Assert
        assertNotNull(result);
        assertEquals(goal, result);
        verifyNoInteractions(competencySvc);
    }

    @Test
    void testSetCompetenciesFromIds_CompetencyNotFound() {
        // Arrange
        Set<UUID> competencyIds = Set.of(COMPETENCY_ID_1);
        when(competencySvc.get(COMPETENCY_ID_1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeServiceException exception = assertThrows(
            RuntimeServiceException.class,
            () -> goalSvc.setCompetenciesFromIds(goal, competencyIds)
        );

        assertTrue(exception.getMessage().contains("Competency not found for id: " + COMPETENCY_ID_1));
        verify(competencySvc).get(COMPETENCY_ID_1);
    }

    @Test
    void testSetCredentialsFromIds_Success() {
        // Arrange
        Set<UUID> credentialIds = Set.of(CREDENTIAL_ID_1, CREDENTIAL_ID_2);
        when(credentialSvc.get(CREDENTIAL_ID_1)).thenReturn(Optional.of(credential1));
        when(credentialSvc.get(CREDENTIAL_ID_2)).thenReturn(Optional.of(credential2));

        // Act
        Goal result = goalSvc.setCredentialsFromIds(goal, credentialIds);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getCredentials());
        assertEquals(2, result.getCredentials().size());
        assertTrue(result.getCredentials().contains(credential1));
        assertTrue(result.getCredentials().contains(credential2));
        
        verify(credentialSvc).get(CREDENTIAL_ID_1);
        verify(credentialSvc).get(CREDENTIAL_ID_2);
    }

    @Test
    void testSetCredentialsFromIds_WithNullIds() {
        // Act
        Goal result = goalSvc.setCredentialsFromIds(goal, null);

        // Assert
        assertNotNull(result);
        assertEquals(goal, result);
        verifyNoInteractions(credentialSvc);
    }

    @Test
    void testSetCredentialsFromIds_CredentialNotFound() {
        // Arrange
        Set<UUID> credentialIds = Set.of(CREDENTIAL_ID_1);
        when(credentialSvc.get(CREDENTIAL_ID_1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeServiceException exception = assertThrows(
            RuntimeServiceException.class,
            () -> goalSvc.setCredentialsFromIds(goal, credentialIds)
        );

        assertTrue(exception.getMessage().contains("Credential not found for id: " + CREDENTIAL_ID_1));
        verify(credentialSvc).get(CREDENTIAL_ID_1);
    }

    @Test
    void testSetLearningResourcesFromIds_Success() {
        // Arrange
        Set<UUID> learningResourceIds = Set.of(LEARNING_RESOURCE_ID_1, LEARNING_RESOURCE_ID_2);
        when(learningResourceSvc.get(LEARNING_RESOURCE_ID_1)).thenReturn(Optional.of(learningResource1));
        when(learningResourceSvc.get(LEARNING_RESOURCE_ID_2)).thenReturn(Optional.of(learningResource2));

        // Act
        Goal result = goalSvc.setLearningResourcesFromIds(goal, learningResourceIds);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getLearningResources());
        assertEquals(2, result.getLearningResources().size());
        assertTrue(result.getLearningResources().contains(learningResource1));
        assertTrue(result.getLearningResources().contains(learningResource2));
        
        verify(learningResourceSvc).get(LEARNING_RESOURCE_ID_1);
        verify(learningResourceSvc).get(LEARNING_RESOURCE_ID_2);
    }

    @Test
    void testSetLearningResourcesFromIds_WithNullIds() {
        // Act
        Goal result = goalSvc.setLearningResourcesFromIds(goal, null);

        // Assert
        assertNotNull(result);
        assertEquals(goal, result);
        verifyNoInteractions(learningResourceSvc);
    }

    @Test
    void testSetLearningResourcesFromIds_LearningResourceNotFound() {
        // Arrange
        Set<UUID> learningResourceIds = Set.of(LEARNING_RESOURCE_ID_1);
        when(learningResourceSvc.get(LEARNING_RESOURCE_ID_1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeServiceException exception = assertThrows(
            RuntimeServiceException.class,
            () -> goalSvc.setLearningResourcesFromIds(goal, learningResourceIds)
        );

        assertTrue(exception.getMessage().contains("Learning Resource not found for id: " + LEARNING_RESOURCE_ID_1));
        verify(learningResourceSvc).get(LEARNING_RESOURCE_ID_1);
    }

    @Test
    void testSetCompetenciesFromIds_EmptySet() {
        // Arrange
        Set<UUID> emptySet = new HashSet<>();

        // Act
        Goal result = goalSvc.setCompetenciesFromIds(goal, emptySet);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getCompetencies());
        assertTrue(result.getCompetencies().isEmpty());
        verifyNoInteractions(competencySvc);
    }

    @Test
    void testSetCredentialsFromIds_EmptySet() {
        // Arrange
        Set<UUID> emptySet = new HashSet<>();

        // Act
        Goal result = goalSvc.setCredentialsFromIds(goal, emptySet);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getCredentials());
        assertTrue(result.getCredentials().isEmpty());
        verifyNoInteractions(credentialSvc);
    }

    @Test
    void testSetLearningResourcesFromIds_EmptySet() {
        // Arrange
        Set<UUID> emptySet = new HashSet<>();

        // Act
        Goal result = goalSvc.setLearningResourcesFromIds(goal, emptySet);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getLearningResources());
        assertTrue(result.getLearningResources().isEmpty());
        verifyNoInteractions(learningResourceSvc);
    }

}