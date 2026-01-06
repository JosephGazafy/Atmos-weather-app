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
@DiscriminatorValue("CREDENTIAL")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalCredential extends PersonalQualification {

    @ManyToOne
    @JoinColumn(name = "qualification_id")
    private Credential credential;

    /**
    * Constructor to create a new PersonalCredential from properties.
    *
    * @param  person     Person for PersonalCredential
    * @param  cred       Comptency for PersonalCredential
    * @param  hasRecord  Whether the assertion has a record
    */
    public PersonalCredential(Person person, Credential cred,
            Boolean hasRecord) {
        this.setCredential(cred);
        this.setHasRecord(hasRecord);
        this.setPerson(person);
    }
}
