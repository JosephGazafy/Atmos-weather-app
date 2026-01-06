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

import com.deloitte.elrr.entity.PersonalCompetency;
import com.deloitte.elrr.repository.PersonalCompetencyRepository;
import com.google.common.collect.Iterables;

@ExtendWith(MockitoExtension.class)
public class PersonalCompetencySvcTest {

    @Mock
    private PersonalCompetencyRepository personalCompetencyRepository;

    @InjectMocks
    private PersonalCompetencySvc personalCompetencySvc;

    private static UUID personalCompetencyId = UUID.randomUUID();

    @Test
    void findAllTest() {
        Mockito.doReturn(getTestPersonalCompetencies())
            .when(personalCompetencyRepository).findAll();
        Iterable<PersonalCompetency> personalCompetencies =
            personalCompetencySvc.findAll();
        assertEquals(Iterables.size(personalCompetencies), 1);
    }

    @Test
    void getTest() {
        Mockito.doReturn(Optional.of(getTestPersonalCompetency()))
                .when(personalCompetencyRepository)
                .findById(personalCompetencyId);
        PersonalCompetency personalCompetency = personalCompetencySvc
            .get(personalCompetencyId)
            .orElse(null);
        assertEquals(personalCompetency.getId(), personalCompetencyId);
    }

    @Test
    void saveTest() {
        personalCompetencySvc.save(getTestPersonalCompetency());
    }

    @Test
    void saveAllTest() {
        personalCompetencySvc.saveAll(getTestPersonalCompetencies());
    }

    @Test
    void deleteTest() {
        Mockito.doReturn(true).when(personalCompetencyRepository)
                .existsById(personalCompetencyId);
        personalCompetencySvc.delete(personalCompetencyId);
    }

    @Test
    void deleteAllTest() {
        personalCompetencySvc.deleteAll();
    }

    @Test
    void updateTest() {
        Mockito.doReturn(true).when(personalCompetencyRepository)
                .existsById(personalCompetencyId);
        personalCompetencySvc.update(getTestPersonalCompetency());
    }

    @Test
    void getIdTest() {
        assertEquals(personalCompetencySvc.getId(getTestPersonalCompetency()),
            personalCompetencyId);
    }

    @Test
    void getRepository() {
        assertEquals(personalCompetencySvc.getRepository(),
            personalCompetencyRepository);
    }

    private PersonalCompetency getTestPersonalCompetency() {
        PersonalCompetency personalCompetency = new PersonalCompetency();
        personalCompetency.setId(personalCompetencyId);
        return personalCompetency;
    }

    private Iterable<PersonalCompetency> getTestPersonalCompetencies() {
        ArrayList<PersonalCompetency> personalCompetencies =
            new ArrayList<PersonalCompetency>();
        personalCompetencies.add(getTestPersonalCompetency());
        return personalCompetencies;
   }

}
