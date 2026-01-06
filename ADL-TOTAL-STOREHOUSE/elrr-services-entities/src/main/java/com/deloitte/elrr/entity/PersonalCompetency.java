package com.deloitte.elrr.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("COMPETENCY")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalCompetency extends PersonalQualification {

    @ManyToOne
    @JoinColumn(name = "qualification_id")
    private Competency competency;

    /**
    * Constructor to create a new PersonalCompetency from properties.
    *
    * @param  person     Person for PersonalComptency
    * @param  comp       Comptency for PersonalCompetency
    * @param  hasRecord  Whether the assertion has a record
    */
    public PersonalCompetency(Person person, Competency comp,
            Boolean hasRecord) {
        this.setCompetency(comp);
        this.setHasRecord(hasRecord);
        this.setPerson(person);
    }
}
