package com.deloitte.elrr.jpa.svc;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.ClientToken;
import com.deloitte.elrr.repository.ClientTokenRepository;

@Service
public class ClientTokenSvc implements CommonSvc<ClientToken, UUID> {

    private final ClientTokenRepository clientTokenRepository;

    /**
     *
     * @param argsClientTokenRepository
     */
    public ClientTokenSvc(
            final ClientTokenRepository argsClientTokenRepository) {
        this.clientTokenRepository = argsClientTokenRepository;
    }

    @Override
    public CrudRepository<ClientToken, UUID> getRepository() {
        return this.clientTokenRepository;
    }

    @Override
    public UUID getId(final ClientToken clientToken) {
        return clientToken.getId();
    }

    @Override
    public ClientToken save(final ClientToken clientToken) {
        return CommonSvc.super.save(clientToken);
    }

    /**
     * check if a client token exists by its ID.
     *
     * @param id the UUID of the client token
     * @return true if the client token exists, false otherwise
     */
    public boolean existsById(final UUID id) {
        return this.clientTokenRepository.existsById(id);
    }

    /**
     * check if a client token exists by its JWT ID.
     *
     * @param jwtId the UUID of the JWT
     * @return true if the client token exists, false otherwise
     */
    public boolean existsByJwtId(final UUID jwtId) {
        return this.clientTokenRepository.existsByJwtId(jwtId);
    }

    /**
     * Find a client token by its JWT ID.
     *
     * @param jwtId the UUID of the JWT
     * @return the ClientToken if found, otherwise null
     */
    public ClientToken findByJwtId(final UUID jwtId) {
        return this.clientTokenRepository.findByJwtId(jwtId);
    }

}
