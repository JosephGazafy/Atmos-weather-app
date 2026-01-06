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

import com.deloitte.elrr.entity.PersonalCredential;
import com.deloitte.elrr.repository.PersonalCredentialRepository;
import com.google.common.collect.Iterables;

@ExtendWith(MockitoExtension.class)
public class PersonalCredentialSvcTest {

    @Mock
    private PersonalCredentialRepository personalCredentialRepository;

    @InjectMocks
    private PersonalCredentialSvc personalCredentialSvc;

    private static UUID personalCredentialId = UUID.randomUUID();

    @Test
    void findAllTest() {
        Mockito.doReturn(getTestPersonalCredentials())
            .when(personalCredentialRepository).findAll();
        Iterable<PersonalCredential> personalCredentials =
            personalCredentialSvc.findAll();
        assertEquals(Iterables.size(personalCredentials), 1);
    }

    @Test
    void getTest() {
        Mockito.doReturn(Optional.of(getTestPersonalCredential()))
                .when(personalCredentialRepository)
                .findById(personalCredentialId);
        PersonalCredential personalCredential = personalCredentialSvc
            .get(personalCredentialId)
            .orElse(null);
        assertEquals(personalCredential.getId(), personalCredentialId);
    }

    @Test
    void saveTest() {
        personalCredentialSvc.save(getTestPersonalCredential());
    }

    @Test
    void saveAllTest() {
        personalCredentialSvc.saveAll(getTestPersonalCredentials());
    }

    @Test
    void deleteTest() {
        Mockito.doReturn(true).when(personalCredentialRepository)
                .existsById(personalCredentialId);
        personalCredentialSvc.delete(personalCredentialId);
    }

    @Test
    void deleteAllTest() {
        personalCredentialSvc.deleteAll();
    }

    @Test
    void updateTest() {
        Mockito.doReturn(true).when(personalCredentialRepository)
                .existsById(personalCredentialId);
        personalCredentialSvc.update(getTestPersonalCredential());
    }

    @Test
    void getIdTest() {
        assertEquals(personalCredentialSvc.getId(getTestPersonalCredential()),
            personalCredentialId);
    }

    @Test
    void getRepository() {
        assertEquals(personalCredentialSvc.getRepository(),
            personalCredentialRepository);
    }

    private PersonalCredential getTestPersonalCredential() {
        PersonalCredential personalCredential = new PersonalCredential();
        personalCredential.setId(personalCredentialId);
        return personalCredential;
    }

    private Iterable<PersonalCredential> getTestPersonalCredentials() {
        ArrayList<PersonalCredential> personalCredentials =
            new ArrayList<PersonalCredential>();
        personalCredentials.add(getTestPersonalCredential());
        return personalCredentials;
   }

}
