package com.deloitte.elrr.jpa.svc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deloitte.elrr.entity.LearningResource;
import com.deloitte.elrr.repository.LearningResourceRepository;
import com.google.common.collect.Iterables;

@ExtendWith(MockitoExtension.class)
public class LearningResourceSvcTest {

    @Mock
    private LearningResourceRepository learningResourceRepository;

    @InjectMocks
    private LearningResourceSvc learningResourceSvc;

    private static UUID learningResourceId = UUID.randomUUID();

    @Test
    void findAllTest() {
        Mockito.doReturn(getTestLearningResources())
            .when(learningResourceRepository).findAll();
        Iterable<LearningResource> learningResources =
            learningResourceSvc.findAll();
        assertEquals(Iterables.size(learningResources), 1);
    }

    @Test
    void getTest() {
        Mockito.doReturn(Optional.of(getTestLearningResource()))
                .when(learningResourceRepository).findById(learningResourceId);
        LearningResource learningResource = learningResourceSvc
            .get(learningResourceId).orElse(null);
        assertEquals(learningResource.getId(), learningResourceId);
    }

    @Test
    void saveTest() {
        learningResourceSvc.save(getTestLearningResource());
    }

    @Test
    void saveAllTest() {
        learningResourceSvc.saveAll(getTestLearningResources());
    }

    @Test
    void deleteTest() {
        Mockito.doReturn(true).when(learningResourceRepository)
                .existsById(learningResourceId);
        learningResourceSvc.delete(learningResourceId);
    }

    @Test
    void deleteAllTest() {
        learningResourceSvc.deleteAll();
    }

    @Test
    void updateTest() {
        Mockito.doReturn(true).when(learningResourceRepository)
                .existsById(learningResourceId);
        learningResourceSvc.update(getTestLearningResource());
    }

    @Test
    void getIdTest() {
        assertEquals(learningResourceSvc.getId(getTestLearningResource()),
            learningResourceId);
    }

    @Test
    void getRepository() {
        assertEquals(learningResourceSvc.getRepository(),
            learningResourceRepository);
    }

    private LearningResource getTestLearningResource() {
        LearningResource learningResource = new LearningResource();
        learningResource.setId(learningResourceId);
        return learningResource;
    }

    private Iterable<LearningResource> getTestLearningResources() {
        ArrayList<LearningResource> learningResources =
            new ArrayList<LearningResource>();
        learningResources.add(getTestLearningResource());
        return learningResources;
   }

}
