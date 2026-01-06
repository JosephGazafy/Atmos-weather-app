package com.deloitte.elrr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.Facility;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, UUID> {

    /**
     * Find facilities by filters.
     *
     * @param id                 ids
     * @param hasExtension       extension keys
     * @param extensionPath      jsonpath queries
     * @param extensionPathMatch jsonpath predicate queries
     * @return list of facilities
     */
    @Query(name = "Facility.findFacilitiesWithFilters", nativeQuery = true)
    List<Facility> findFacilitiesWithFilters(@Param("id") UUID[] id,
            @Param("hasExtension") String[] hasExtension,
            @Param("extensionPath") String[] extensionPath,
            @Param("extensionPathMatch") String[] extensionPathMatch);

    /**
     * Convenience overload accepting Filter object.
     *
     * @param filter filter object
     * @return list of facilities
     */
    default List<Facility> findFacilitiesWithFilters(
            final Facility.Filter filter) {
        return findFacilitiesWithFilters(filter.getId(),
                filter.getHasExtension(), filter.getExtensionPath(),
                filter.getExtensionPathMatch());
    }

}
