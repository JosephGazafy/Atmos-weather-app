package com.deloitte.elrr.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedNativeQuery;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("COMPETENCY")
@NamedNativeQuery(
    name = "Competency.findCompetenciesWithFilters",
    query =
    """
    SELECT DISTINCT q.* FROM {h-schema}qualification q
    WHERE q.type = 'COMPETENCY'
    -- by ID
    AND (CAST(:id AS uuid[]) IS NULL OR q.id = ANY(:id))
    -- by presence of (all) extension keys
    AND (CAST(:hasExtension AS text[]) IS NULL OR
        q.extensions \\?\\?& CAST(:hasExtension AS text[]))
    -- by returning items from all jsonpath queries
    AND (CAST(:extensionPath AS text[]) IS NULL OR
        (SELECT bool_and(q.extensions @\\?\\? path::jsonpath)
         FROM unnest(CAST(:extensionPath AS text[])) AS path))
    -- by returning items from all jsonpath predicates
    AND (CAST(:extensionPathMatch AS text[]) IS NULL OR
        (SELECT bool_and(q.extensions @@ path::jsonpath)
         FROM unnest(CAST(:extensionPathMatch AS text[])) AS path))
    -- by identifier
    AND (CAST(:identifier AS text[]) IS NULL OR
        q.identifier ILIKE ANY(CAST(:identifier AS text[])))
    -- by identifier URL
    AND (CAST(:identifierUrl AS text[]) IS NULL OR
        q.identifier_url = ANY(CAST(:identifierUrl AS text[])))
    -- by code
    AND (CAST(:code AS text[]) IS NULL OR
        q.code = ANY(CAST(:code AS text[])))
    """,
    resultClass = Competency.class
)
@Getter
@Setter
public class Competency extends AbstractQualification {
    @Getter
    @Setter
    public static class Filter extends AbstractQualification.Filter { }
}
