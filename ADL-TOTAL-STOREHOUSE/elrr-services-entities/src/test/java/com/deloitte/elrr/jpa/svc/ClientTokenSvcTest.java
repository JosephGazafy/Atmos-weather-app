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

import com.deloitte.elrr.entity.ClientToken;
import com.deloitte.elrr.repository.ClientTokenRepository;
import com.google.common.collect.Iterables;

@ExtendWith(MockitoExtension.class)
public class ClientTokenSvcTest {

    @Mock
    private ClientTokenRepository clientTokenRepository;

    @InjectMocks
    private ClientTokenSvc clientTokenSvc;

    private static UUID clientTokenId = UUID.randomUUID();

    @Test
    void findAllTest() {
        Mockito.doReturn(getTestClientTokens()).when(clientTokenRepository)
                .findAll();
        Iterable<ClientToken> clientTokens = clientTokenSvc.findAll();
        assertEquals(Iterables.size(clientTokens), 1);
    }

    @Test
    void getTest() {
        Mockito.doReturn(Optional.of(getTestClientToken()))
                .when(clientTokenRepository).findById(clientTokenId);
        ClientToken clientToken = clientTokenSvc.get(clientTokenId)
                .orElse(null);
        assertEquals(clientToken.getId(), clientTokenId);
    }

    @Test
    void saveTest() {
        clientTokenSvc.save(getTestClientToken());
    }

    @Test
    void saveAllTest() {
        clientTokenSvc.saveAll(getTestClientTokens());
    }

    @Test
    void deleteTest() {
        Mockito.doReturn(true).when(clientTokenRepository)
                .existsById(clientTokenId);
        clientTokenSvc.delete(clientTokenId);
    }

    @Test
    void deleteAllTest() {
        clientTokenSvc.deleteAll();
    }

    @Test
    void updateTest() {
        Mockito.doReturn(true).when(clientTokenRepository)
                .existsById(clientTokenId);
        clientTokenSvc.update(getTestClientToken());
    }

    @Test
    void getIdTest() {
        assertEquals(clientTokenSvc.getId(getTestClientToken()), clientTokenId);
    }

    @Test
    void getRepository() {
        assertEquals(clientTokenSvc.getRepository(), clientTokenRepository);
    }

    @Test
    void existsByIdTest() {
        Mockito.doReturn(true).when(clientTokenRepository)
                .existsById(clientTokenId);
        assertEquals(clientTokenSvc.existsById(clientTokenId), true);
    }

    @Test
    void existsByJwtIdTest() {
        UUID jwtId = UUID.randomUUID();
        Mockito.doReturn(true).when(clientTokenRepository)
                .existsByJwtId(jwtId);
        assertEquals(clientTokenSvc.existsByJwtId(jwtId), true);
    }

    @Test
    void findByJwtIdTest() {
        UUID jwtId = UUID.randomUUID();
        Mockito.doReturn(getTestClientToken())
                .when(clientTokenRepository).findByJwtId(jwtId);
        ClientToken clientToken = clientTokenSvc.findByJwtId(jwtId);
        assertEquals(clientToken.getId(), clientTokenId);
    }

    private ClientToken getTestClientToken() {
        ClientToken clientToken = new ClientToken();
        clientToken.setId(clientTokenId);
        return clientToken;
    }

    private Iterable<ClientToken> getTestClientTokens() {
        ArrayList<ClientToken> clientTokens = new ArrayList<ClientToken>();
        clientTokens.add(getTestClientToken());
        return clientTokens;
    }
}