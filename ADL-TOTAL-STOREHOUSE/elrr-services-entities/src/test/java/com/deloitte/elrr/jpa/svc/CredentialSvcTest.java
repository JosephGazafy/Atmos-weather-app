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

import com.deloitte.elrr.entity.Credential;
import com.deloitte.elrr.repository.CredentialRepository;
import com.google.common.collect.Iterables;

@ExtendWith(MockitoExtension.class)
public class CredentialSvcTest {

    @Mock
    private CredentialRepository credentialRepository;

    @InjectMocks
    private CredentialSvc credentialSvc;

    private static UUID credentialId = UUID.randomUUID();

    @Test
    void findAllTest() {
        Mockito.doReturn(getTestCredentials()).when(credentialRepository)
                .findAll();
        Iterable<Credential> credentials = credentialSvc.findAll();
        assertEquals(Iterables.size(credentials), 1);
    }

    @Test
    void getTest() {
        Mockito.doReturn(Optional.of(getTestCredential()))
                .when(credentialRepository).findById(credentialId);
        Credential credential = credentialSvc.get(credentialId)
            .orElse(null);
        assertEquals(credential.getId(), credentialId);
    }

    @Test
    void saveTest() {
        credentialSvc.save(getTestCredential());
    }

    @Test
    void saveAllTest() {
        credentialSvc.saveAll(getTestCredentials());
    }

    @Test
    void deleteTest() {
        Mockito.doReturn(true).when(credentialRepository)
                .existsById(credentialId);
        credentialSvc.delete(credentialId);
    }

    @Test
    void deleteAllTest() {
        credentialSvc.deleteAll();
    }

    @Test
    void updateTest() {
        Mockito.doReturn(true).when(credentialRepository)
                .existsById(credentialId);
        credentialSvc.update(getTestCredential());
    }

    @Test
    void getIdTest() {
        assertEquals(credentialSvc.getId(getTestCredential()), credentialId);
    }

    @Test
    void getRepository() {
        assertEquals(credentialSvc.getRepository(), credentialRepository);
    }

    private Credential getTestCredential() {
        Credential credential = new Credential();
        credential.setId(credentialId);
        return credential;
    }

    private Iterable<Credential> getTestCredentials() {
        ArrayList<Credential> credentials = new ArrayList<Credential>();
        credentials.add(getTestCredential());
        return credentials;
   }

}
