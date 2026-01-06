package com.deloitte.elrr.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.ClientToken;

@Repository
public interface ClientTokenRepository
                extends JpaRepository<ClientToken, UUID> {
        /**
         * Check if a client token exists by its JWT ID.
         *
         * @param jwtId the UUID of the JWT
         * @return true if the client token exists, false otherwise
         */
        Boolean existsByJwtId(UUID jwtId);

        /**
         * Find a client token by its JWT ID.
         *
         * @param jwtId the UUID of the JWT
         * @return the ClientToken if found, otherwise null
         */
        ClientToken findByJwtId(UUID jwtId);
}
