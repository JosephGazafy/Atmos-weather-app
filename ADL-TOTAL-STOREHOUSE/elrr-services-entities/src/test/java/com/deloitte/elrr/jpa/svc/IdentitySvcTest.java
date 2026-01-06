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

import com.deloitte.elrr.entity.Identity;
import com.deloitte.elrr.repository.IdentityRepository;
import com.google.common.collect.Iterables;

@ExtendWith(MockitoExtension.class)
public class IdentitySvcTest {

    @Mock
    private IdentityRepository identityRepository;

    @InjectMocks
    private IdentitySvc identitySvc;

    private static UUID identityId = UUID.randomUUID();

    @Test
    void findAllTest() {
        Mockito.doReturn(getTestIdentities()).when(identityRepository)
                .findAll();
        Iterable<Identity> identities = identitySvc.findAll();
        assertEquals(Iterables.size(identities), 1);
    }

    @Test
    void getTest() {
        Mockito.doReturn(Optional.of(getTestIdentity()))
                .when(identityRepository).findById(identityId);
        Identity identity = identitySvc.get(identityId)
            .orElse(null);
        assertEquals(identity.getId(), identityId);
    }

    @Test
    void saveTest() {
        identitySvc.save(getTestIdentity());
    }

    @Test
    void saveAllTest() {
        identitySvc.saveAll(getTestIdentities());
    }

    @Test
    void deleteTest() {
        Mockito.doReturn(true).when(identityRepository)
                .existsById(identityId);
        identitySvc.delete(identityId);
    }

    @Test
    void deleteAllTest() {
        identitySvc.deleteAll();
    }

    @Test
    void updateTest() {
        Mockito.doReturn(true).when(identityRepository)
                .existsById(identityId);
        identitySvc.update(getTestIdentity());
    }

    @Test
    void getIdTest() {
        assertEquals(identitySvc.getId(getTestIdentity()), identityId);
    }

    @Test
    void getRepository() {
        assertEquals(identitySvc.getRepository(), identityRepository);
    }

    private Identity getTestIdentity() {
        Identity identity = new Identity();
        identity.setId(identityId);
        return identity;
    }

    private Iterable<Identity> getTestIdentities() {
        ArrayList<Identity> identities = new ArrayList<Identity>();
        identities.add(getTestIdentity());
        return identities;
   }

}
