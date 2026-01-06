package com.deloitte.elrr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,
        UUID> {

    /**
     * Find organizations by filters.
     *
     * @param id                 ids
     * @param hasExtension       extension keys
     * @param extensionPath      jsonpath queries
     * @param extensionPathMatch jsonpath predicate queries
     * @param name               organization name
     * @param description        organization description
     * @return list of organizations
     */
    @Query(name = "Organization.findOrganizationsWithFilters",
           nativeQuery = true)
    List<Organization> findOrganizationsWithFilters(@Param("id") UUID[] id,
            @Param("hasExtension") String[] hasExtension,
            @Param("extensionPath") String[] extensionPath,
            @Param("extensionPathMatch") String[] extensionPathMatch,
            @Param("name") String[] name,
            @Param("description") String[] description);

    /**
     * Convenience overload accepting Filter object.
     *
     * @param filter filter object
     * @return list of organizations
     */
    default List<Organization> findOrganizationsWithFilters(
            final Organization.Filter filter) {
        return findOrganizationsWithFilters(
                filter.getId(),
                filter.getHasExtension(),
                filter.getExtensionPath(),
                filter.getExtensionPathMatch(),
                filter.getName(),
                filter.getDescription());
    }

}
