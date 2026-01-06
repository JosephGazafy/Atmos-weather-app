package com.deloitte.elrr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "client_token")
@EntityListeners(AuditingEntityListener.class)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientToken extends Auditable {

    @Column(name = "jwt_id")
    private UUID jwtId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "jwt_payload", columnDefinition = "JSONB")
    private Map<String, Object> jwtPayload;

    @Column(name = "label", length = 100)
    private String label;

    @Override
    public String toString() {
        return "ClientToken [id=" + id + "]";
    }
}
