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

import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.repository.CompetencyRepository;
import com.google.common.collect.Iterables;

@ExtendWith(MockitoExtension.class)
public class CompetencySvcTest {

    @Mock
    private CompetencyRepository competencyRepository;

    @InjectMocks
    private CompetencySvc competencySvc;

    private static UUID competencyId = UUID.randomUUID();

    @Test
    void findAllTest() {
        Mockito.doReturn(getTestCompetencies()).when(competencyRepository)
                .findAll();
        Iterable<Competency> competencies = competencySvc.findAll();
        assertEquals(Iterables.size(competencies), 1);
    }

    @Test
    void getTest() {
        Mockito.doReturn(Optional.of(getTestCompetency()))
                .when(competencyRepository).findById(competencyId);
        Competency competency = competencySvc.get(competencyId)
            .orElse(null);
        assertEquals(competency.getId(), competencyId);
    }

    @Test
    void saveTest() {
        competencySvc.save(getTestCompetency());
    }

    @Test
    void saveAllTest() {
        competencySvc.saveAll(getTestCompetencies());
    }

    @Test
    void deleteTest() {
        Mockito.doReturn(true).when(competencyRepository)
                .existsById(competencyId);
        competencySvc.delete(competencyId);
    }

    @Test
    void deleteAllTest() {
        competencySvc.deleteAll();
    }

    @Test
    void updateTest() {
        Mockito.doReturn(true).when(competencyRepository)
                .existsById(competencyId);
        competencySvc.update(getTestCompetency());
    }

    @Test
    void getIdTest() {
        assertEquals(competencySvc.getId(getTestCompetency()), competencyId);
    }

    @Test
    void getRepository() {
        assertEquals(competencySvc.getRepository(), competencyRepository);
    }

    private Competency getTestCompetency() {
        Competency competency = new Competency();
        competency.setId(competencyId);
        return competency;
    }

    private Iterable<Competency> getTestCompetencies() {
        ArrayList<Competency> competencies = new ArrayList<Competency>();
        competencies.add(getTestCompetency());
        return competencies;
   }

}
