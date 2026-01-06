package com.deloitte.elrr.entity;

import java.util.Map;
import java.net.URI;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Extensible extends Auditable {

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "extensions", columnDefinition = "JSONB")
    private Map<URI, Object> extensions;

    /**
     * Filter criteria for extensions.
     * All fields are optional and correspond to query parameters.
     */
    @Getter
    @Setter
    public abstract static class Filter extends Auditable.Filter {
        /**
         * Optional filter for extension keys.
         */
        private String[] hasExtension;

        /**
         * Optional filter for extension JSONPath expressions.
         */
        private String[] extensionPath;

        /**
         * Optional filter for extension JSONPath predicate expressions.
         */
        private String[] extensionPathMatch;
    }
}
