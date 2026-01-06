package com.deloitte.elrr.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "facility")
@NamedNativeQuery(
    name = "Facility.findFacilitiesWithFilters",
    query =
    """
    SELECT DISTINCT f.* FROM {h-schema}facility f
    -- by ID
    WHERE (CAST(:id AS uuid[]) IS NULL OR f.id = ANY(:id))
    -- by presence of (all) extension keys
    AND (CAST(:hasExtension AS text[]) IS NULL OR
        f.extensions \\?\\?& CAST(:hasExtension AS text[]))
    -- by returning items from all jsonpath queries
    AND (CAST(:extensionPath AS text[]) IS NULL OR
        (SELECT bool_and(f.extensions @\\?\\? path::jsonpath)
         FROM unnest(CAST(:extensionPath AS text[])) AS path))
    -- by returning items from all jsonpath predicates
    AND (CAST(:extensionPathMatch AS text[]) IS NULL OR
        (SELECT bool_and(f.extensions @@ path::jsonpath)
         FROM unnest(CAST(:extensionPathMatch AS text[])) AS path))
    """,
    resultClass = Facility.class
)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Facility extends Extensible {

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "operational_status")
    private String operationalStatus;

    @Column(name = "facility_security_level")
    private String facilitySecurityLevel;

    @Override
    public String toString() {
        return "Facility [name = " + name + ", description=" + description
        + ", id=" + id + ", location=" + location + ", operational_status="
        + operationalStatus + ", facility_security_level="
        + facilitySecurityLevel + "]";
    }

    @Getter
    @Setter
    public static class Filter extends Extensible.Filter { }
}
