package com.deloitte.elrr.jpa.svc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.repository.PersonRepository;
import com.google.common.collect.Iterables;

@ExtendWith(MockitoExtension.class)
public class PersonSvcTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonSvc personSvc;

    private static UUID personId = UUID.randomUUID();

    @Test
    void findAllTest() {
        Mockito.doReturn(getTestPeople()).when(personRepository)
                .findAll();
        Iterable<Person> people = personSvc.findAll();
        assertEquals(Iterables.size(people), 1);
    }

    @Test
    void getTest() {
        Mockito.doReturn(Optional.of(getTestPerson()))
                .when(personRepository).findById(personId);
        Person person = personSvc.get(personId)
            .orElse(null);
        assertEquals(person.getId(), personId);
    }

    @Test
    void saveTest() {
        personSvc.save(getTestPerson());
    }

    @Test
    void saveAllTest() {
        personSvc.saveAll(getTestPeople());
    }

    @Test
    void deleteTest() {
        Mockito.doReturn(true).when(personRepository)
                .existsById(personId);
        personSvc.delete(personId);
    }

    @Test
    void deleteAllTest() {
        personSvc.deleteAll();
    }

    @Test
    void updateTest() {
        Mockito.doReturn(true).when(personRepository)
                .existsById(personId);
        personSvc.update(getTestPerson());
    }

    @Test
    void getIdTest() {
        assertEquals(personSvc.getId(getTestPerson()), personId);
    }

    @Test
    void getRepository() {
        assertEquals(personSvc.getRepository(), personRepository);
    }

    @Test
    void findPersonsWithFiltersTest() {
        Mockito.doReturn(getTestPeople()).when(personRepository)
                .findPersonsWithFilters(Mockito.any(Person.Filter.class));
        Person.Filter filter = new Person.Filter();
        Iterable<Person> people = personSvc.findPersonsWithFilters(filter);
        assertEquals(Iterables.size(people), 1);
    }

    private Person getTestPerson() {
        Person person = new Person();
        person.setId(personId);
        return person;
    }

    private Iterable<Person> getTestPeople() {
        ArrayList<Person> people = new ArrayList<Person>();
        people.add(getTestPerson());
        return people;
   }

}
