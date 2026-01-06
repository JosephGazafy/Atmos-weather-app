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

import com.deloitte.elrr.entity.LearningRecord;
import com.deloitte.elrr.repository.LearningRecordRepository;
import com.google.common.collect.Iterables;

@ExtendWith(MockitoExtension.class)
public class LearningRecordSvcTest {

    @Mock
    private LearningRecordRepository learningRecordRepository;

    @InjectMocks
    private LearningRecordSvc learningRecordSvc;

    private static UUID learningRecordId = UUID.randomUUID();

    @Test
    void findAllTest() {
        Mockito.doReturn(getTestLearningRecords())
            .when(learningRecordRepository).findAll();
        Iterable<LearningRecord> learningRecords =
            learningRecordSvc.findAll();
        assertEquals(Iterables.size(learningRecords), 1);
    }

    @Test
    void getTest() {
        Mockito.doReturn(Optional.of(getTestLearningRecord()))
                .when(learningRecordRepository).findById(learningRecordId);
        LearningRecord learningRecord = learningRecordSvc
            .get(learningRecordId).orElse(null);
        assertEquals(learningRecord.getId(), learningRecordId);
    }

    @Test
    void saveTest() {
        learningRecordSvc.save(getTestLearningRecord());
    }

    @Test
    void saveAllTest() {
        learningRecordSvc.saveAll(getTestLearningRecords());
    }

    @Test
    void deleteTest() {
        Mockito.doReturn(true).when(learningRecordRepository)
                .existsById(learningRecordId);
        learningRecordSvc.delete(learningRecordId);
    }

    @Test
    void deleteAllTest() {
        learningRecordSvc.deleteAll();
    }

    @Test
    void updateTest() {
        Mockito.doReturn(true).when(learningRecordRepository)
                .existsById(learningRecordId);
        learningRecordSvc.update(getTestLearningRecord());
    }

    @Test
    void getIdTest() {
        assertEquals(learningRecordSvc.getId(getTestLearningRecord()),
            learningRecordId);
    }

    @Test
    void getRepository() {
        assertEquals(learningRecordSvc.getRepository(),
            learningRecordRepository);
    }

    private LearningRecord getTestLearningRecord() {
        LearningRecord learningRecord = new LearningRecord();
        learningRecord.setId(learningRecordId);
        return learningRecord;
    }

    private Iterable<LearningRecord> getTestLearningRecords() {
        ArrayList<LearningRecord> learningRecords =
            new ArrayList<LearningRecord>();
        learningRecords.add(getTestLearningRecord());
        return learningRecords;
   }

}
