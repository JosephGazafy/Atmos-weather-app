/**
 *
 */
package com.deloitte.elrr.entity;

import java.time.ZonedDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author mnelakurti
 * This Entity class is
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Auditable extends Entity {

    @Column(name = "inserted_date", updatable = false)
    @CreationTimestamp
    private ZonedDateTime insertedDate;

    @Column(name = "updated_by", length = 20)
    @LastModifiedBy
    private String updatedBy;

    @Column(name = "last_modified")
    @UpdateTimestamp

    private ZonedDateTime lastModified;

    public abstract static class Filter extends Entity.Filter { }
}
