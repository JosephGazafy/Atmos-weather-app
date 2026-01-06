/**
 *
 */
package com.deloitte.elrr.jpa.svc;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mnelakurti
 *
 */

@Service
@Slf4j
public class PersonSvc implements CommonSvc<Person, UUID> {

    private final PersonRepository personRepository;

    @Autowired
    private LocationSvc locationSvc;

    @Autowired
    private EmailSvc emailSvc;

    @Autowired
    private PhoneSvc phoneSvc;

    /**
     *
     * @param argsPersonRepository
     */
    public PersonSvc(final PersonRepository argsPersonRepository) {
        this.personRepository = argsPersonRepository;
    }

    /**
     *
     * @return CrudRepository<Person, Long>
     */
    @Override
    public CrudRepository<Person, UUID> getRepository() {
        return this.personRepository;
    }

    /**
     *
     * @return Long
     */
    @Override
    public UUID getId(final Person person) {
        return person.getId();
    }

    /**
     *
     * @return Person
     */
    @Override
    public Person save(final Person person) {
        // Save locations
        if (person.getMailingAddress() != null) {
            person.setMailingAddress(
                    locationSvc.save(person.getMailingAddress()));
        }
        if (person.getPhysicalAddress() != null) {
            person.setPhysicalAddress(
                    locationSvc.save(person.getPhysicalAddress()));
        }
        if (person.getShippingAddress() != null) {
            person.setShippingAddress(
                    locationSvc.save(person.getShippingAddress()));
        }
        if (person.getBillingAddress() != null) {
            person.setBillingAddress(
                    locationSvc.save(person.getBillingAddress()));
        }
        if (person.getOnCampusAddress() != null) {
            person.setOnCampusAddress(
                    locationSvc.save(person.getOnCampusAddress()));
        }
        if (person.getOffCampusAddress() != null) {
            person.setOffCampusAddress(
                    locationSvc.save(person.getOffCampusAddress()));
        }
        if (person.getTemporaryAddress() != null) {
            person.setTemporaryAddress(
                    locationSvc.save(person.getTemporaryAddress()));
        }
        if (person.getPermanentStudentAddress() != null) {
            person.setPermanentStudentAddress(
                    locationSvc.save(person.getPermanentStudentAddress()));
        }
        if (person.getEmploymentAddress() != null) {
            person.setEmploymentAddress(
                    locationSvc.save(person.getEmploymentAddress()));
        }
        if (person.getTimeOfAdmissionAddress() != null) {
            person.setTimeOfAdmissionAddress(
                    locationSvc.save(person.getTimeOfAdmissionAddress()));
        }
        if (person.getFatherAddress() != null) {
            person.setFatherAddress(
                    locationSvc.save(person.getFatherAddress()));
        }
        if (person.getMotherAddress() != null) {
            person.setMotherAddress(
                    locationSvc.save(person.getMotherAddress()));
        }
        if (person.getGuardianAddress() != null) {
            person.setGuardianAddress(
                    locationSvc.save(person.getGuardianAddress()));
        }
        if (person.getBirthplaceAddress() != null) {
            person.setBirthplaceAddress(
                    locationSvc.save(person.getBirthplaceAddress()));
        }

        // Save email addresses and phone numbers
        saveAllInfo(person);

        return CommonSvc.super.save(person);
    }

    /**
     * Find persons with optional filters using Person.Filter object.
     *
     * @param filter Person.Filter containing all filter criteria
     * @return List of persons matching the criteria
     */
    public List<Person> findPersonsWithFilters(final Person.Filter filter) {
        return personRepository.findPersonsWithFilters(filter);
    }

    private void saveAllInfo(final Person person) {
        // Save email addresses if present
        if (person.getEmailAddresses() != null) {
            emailSvc.saveAll(person.getEmailAddresses());
        }
        // Save phone numbers if present
        if (person.getPhoneNumbers() != null) {
            phoneSvc.saveAll(person.getPhoneNumbers());
        }
    }
}
