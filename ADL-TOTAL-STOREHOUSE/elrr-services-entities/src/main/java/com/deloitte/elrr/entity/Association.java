package com.deloitte.elrr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "association")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Association extends Auditable {

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "association_type")
    private String associationType;

    @Override
    public final String toString() {
        return "Association [id=" + id + ", organization=" + organization
            + ", person=" + person + ", associationType=" + associationType
            + "]";
    }

}
