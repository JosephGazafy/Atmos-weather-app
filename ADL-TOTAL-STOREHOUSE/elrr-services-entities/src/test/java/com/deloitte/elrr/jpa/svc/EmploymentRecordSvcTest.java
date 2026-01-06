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

import com.deloitte.elrr.entity.EmploymentRecord;
import com.deloitte.elrr.repository.EmploymentRecordRepository;
import com.google.common.collect.Iterables;

@ExtendWith(MockitoExtension.class)
public class EmploymentRecordSvcTest {

    @Mock
    private EmploymentRecordRepository employmentRecordRepository;

    @InjectMocks
    private EmploymentRecordSvc employmentRecordSvc;

    private static UUID employmentRecordId = UUID.randomUUID();

    @Test
    void findAllTest() {
        Mockito.doReturn(getTestEmploymentRecords())
            .when(employmentRecordRepository).findAll();
        Iterable<EmploymentRecord> employmentRecords =
            employmentRecordSvc.findAll();
        assertEquals(Iterables.size(employmentRecords), 1);
    }

    @Test
    void getTest() {
        Mockito.doReturn(Optional.of(getTestEmploymentRecord()))
                .when(employmentRecordRepository).findById(employmentRecordId);
        EmploymentRecord employmentRecord = employmentRecordSvc
            .get(employmentRecordId).orElse(null);
        assertEquals(employmentRecord.getId(), employmentRecordId);
    }

    @Test
    void saveTest() {
        employmentRecordSvc.save(getTestEmploymentRecord());
    }

    @Test
    void saveAllTest() {
        employmentRecordSvc.saveAll(getTestEmploymentRecords());
    }

    @Test
    void deleteTest() {
        Mockito.doReturn(true).when(employmentRecordRepository)
                .existsById(employmentRecordId);
        employmentRecordSvc.delete(employmentRecordId);
    }

    @Test
    void deleteAllTest() {
        employmentRecordSvc.deleteAll();
    }

    @Test
    void updateTest() {
        Mockito.doReturn(true).when(employmentRecordRepository)
                .existsById(employmentRecordId);
        employmentRecordSvc.update(getTestEmploymentRecord());
    }

    @Test
    void getIdTest() {
        assertEquals(employmentRecordSvc.getId(getTestEmploymentRecord()),
            employmentRecordId);
    }

    @Test
    void getRepository() {
        assertEquals(employmentRecordSvc.getRepository(),
            employmentRecordRepository);
    }

    private EmploymentRecord getTestEmploymentRecord() {
        EmploymentRecord employmentRecord = new EmploymentRecord();
        employmentRecord.setId(employmentRecordId);
        return employmentRecord;
    }

    private Iterable<EmploymentRecord> getTestEmploymentRecords() {
        ArrayList<EmploymentRecord> employmentRecords =
            new ArrayList<EmploymentRecord>();
        employmentRecords.add(getTestEmploymentRecord());
        return employmentRecords;
   }

}
