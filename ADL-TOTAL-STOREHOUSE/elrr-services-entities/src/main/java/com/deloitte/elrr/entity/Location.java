package com.deloitte.elrr.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "location")
@NamedNativeQuery(
    name = "Location.findLocationsWithFilters",
    query =
    """
    SELECT DISTINCT l.* FROM {h-schema}location l
    -- by ID
    WHERE (CAST(:id AS uuid[]) IS NULL OR l.id = ANY(:id))
    -- by presence of (all) extension keys
    AND (CAST(:hasExtension AS text[]) IS NULL OR
        l.extensions \\?\\?& CAST(:hasExtension AS text[]))
    -- by returning items from all jsonpath queries
    AND (CAST(:extensionPath AS text[]) IS NULL OR
        (SELECT bool_and(l.extensions @\\?\\? path::jsonpath)
         FROM unnest(CAST(:extensionPath AS text[])) AS path))
    -- by returning items from all jsonpath predicates
    AND (CAST(:extensionPathMatch AS text[]) IS NULL OR
        (SELECT bool_and(l.extensions @@ path::jsonpath)
         FROM unnest(CAST(:extensionPathMatch AS text[])) AS path))
    """,
    resultClass = Location.class
)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Location extends Extensible {

    @Column(name = "street_number_and_name")
    private String streetNumberAndName;

    @Column(name = "apartment_room_suite_number")
    private String apartmentRoomSuiteNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "state_abbreviation")
    private String stateAbbreviation;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "county")
    private String county;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Override
    public String toString() {
        return "Location [streetNumberAndname = " + streetNumberAndName
        + ", apartmentRoomSuiteNumber=" + apartmentRoomSuiteNumber + ", id="
        + id + ", city=" + city + ", stateAbbreviation=" + stateAbbreviation
        + ", postalCode=" + postalCode + ", county=" + county + ", countryCode="
        + countryCode + ", latitude=" + latitude + ", longitude=" + longitude
        + "]";
    }

    @Getter
    @Setter
    public static class Filter extends Extensible.Filter { }

}
