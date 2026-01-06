package com.deloitte.elrr.entity;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "person_qualification")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Getter
@Setter
public class PersonalQualification extends Auditable {

    @ManyToOne
    @JoinColumn(name = "person_id")
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected Person person;

    @Column(name = "has_record")
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected Boolean hasRecord;

    @Column(name = "expires")
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected ZonedDateTime expires;
}
