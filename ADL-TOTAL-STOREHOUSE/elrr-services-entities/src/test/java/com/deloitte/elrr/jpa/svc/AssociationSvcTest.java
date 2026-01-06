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

import com.deloitte.elrr.entity.Association;
import com.deloitte.elrr.repository.AssociationRepository;
import com.google.common.collect.Iterables;

@ExtendWith(MockitoExtension.class)
public class AssociationSvcTest {

    @Mock
    private AssociationRepository associationRepository;

    @InjectMocks
    private AssociationSvc associationSvc;

    private static UUID associationId = UUID.randomUUID();

    @Test
    void findAllTest() {
        Mockito.doReturn(getTestAssociations()).when(associationRepository)
                .findAll();
        Iterable<Association> associations = associationSvc.findAll();
        assertEquals(Iterables.size(associations), 1);
    }

    @Test
    void getTest() {
        Mockito.doReturn(Optional.of(getTestAssociation()))
                .when(associationRepository).findById(associationId);
        Association association = associationSvc.get(associationId)
            .orElse(null);
        assertEquals(association.getId(), associationId);
    }

    @Test
    void saveTest() {
        associationSvc.save(getTestAssociation());
    }

    @Test
    void saveAllTest() {
        associationSvc.saveAll(getTestAssociations());
    }

    @Test
    void deleteTest() {
        Mockito.doReturn(true).when(associationRepository)
                .existsById(associationId);
        associationSvc.delete(associationId);
    }

    @Test
    void deleteAllTest() {
        associationSvc.deleteAll();
    }

    @Test
    void updateTest() {
        Mockito.doReturn(true).when(associationRepository)
                .existsById(associationId);
        associationSvc.update(getTestAssociation());
    }

    @Test
    void getIdTest() {
        assertEquals(associationSvc.getId(getTestAssociation()), associationId);
    }

    @Test
    void getRepository() {
        assertEquals(associationSvc.getRepository(), associationRepository);
    }

    private Association getTestAssociation() {
        Association association = new Association();
        association.setId(associationId);
        return association;
    }

    private Iterable<Association> getTestAssociations() {
        ArrayList<Association> associations = new ArrayList<Association>();
        associations.add(getTestAssociation());
        return associations;
   }

}
