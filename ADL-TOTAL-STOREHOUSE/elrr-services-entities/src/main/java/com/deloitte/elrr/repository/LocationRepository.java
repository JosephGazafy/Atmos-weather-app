package com.deloitte.elrr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

    /**
     * Find locations by filters.
     *
     * @param id                 ids
     * @param hasExtension       extension keys
     * @param extensionPath      jsonpath queries
     * @param extensionPathMatch jsonpath predicate queries
     * @return list of locations
     */
    @Query(name = "Location.findLocationsWithFilters", nativeQuery = true)
    List<Location> findLocationsWithFilters(@Param("id") UUID[] id,
            @Param("hasExtension") String[] hasExtension,
            @Param("extensionPath") String[] extensionPath,
            @Param("extensionPathMatch") String[] extensionPathMatch);

    /**
     * Convenience overload accepting Filter object.
     *
     * @param filter filter object
     * @return list of locations
     */
    default List<Location> findLocationsWithFilters(
            final Location.Filter filter) {
        return findLocationsWithFilters(filter.getId(),
                filter.getHasExtension(), filter.getExtensionPath(),
                filter.getExtensionPathMatch());
    }

}
