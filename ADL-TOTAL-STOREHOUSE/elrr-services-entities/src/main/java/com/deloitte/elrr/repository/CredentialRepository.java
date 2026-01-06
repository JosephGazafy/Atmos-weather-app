package com.deloitte.elrr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.Credential;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, UUID> {

    /**
     * @param identifier
     * @return Credential
     */
    Credential findByIdentifier(String identifier);

    /**
     * Find credentials by optional filters.
     *
     * @param id Optional credential ID filter
     * @param hasExtension Optional filter for extension keys
     * @param extensionPath Optional filter for JSONPath expressions
     * @param extensionPathMatch Optional filter for JSONPath predicates
     * @param identifier Optional filter for credential identifier
     * @param identifierUrl Optional filter for credential identifier URL
     * @param code Optional filter for credential code
     * @return List of credentials matching the criteria
     */
    List<Credential> findCredentialsWithFilters(
        @Param("id") UUID[] id,
        @Param("hasExtension") String[] hasExtension,
        @Param("extensionPath") String[] extensionPath,
        @Param("extensionPathMatch") String[] extensionPathMatch,
        @Param("identifier") String[] identifier,
        @Param("identifierUrl") String[] identifierUrl,
        @Param("code") String[] code
    );

    /**
     * Find credentials using a filter object.
     *
     * @param filter Credential.Filter containing all filter criteria
     * @return List of credentials matching the criteria
     */
    default List<Credential> findCredentialsWithFilters(
        Credential.Filter filter) {
        return findCredentialsWithFilters(
            filter.getId(),
            filter.getHasExtension(),
            filter.getExtensionPath(),
            filter.getExtensionPathMatch(),
            filter.getIdentifier(),
            filter.getIdentifierUrl(),
            filter.getCode()
        );
    }
}
