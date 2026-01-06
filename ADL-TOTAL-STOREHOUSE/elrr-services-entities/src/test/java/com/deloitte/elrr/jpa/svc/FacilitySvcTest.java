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

import com.deloitte.elrr.entity.Facility;
import com.deloitte.elrr.repository.FacilityRepository;
import com.google.common.collect.Iterables;

@ExtendWith(MockitoExtension.class)
public class FacilitySvcTest {

    @Mock
    private FacilityRepository facilityRepository;

    @InjectMocks
    private FacilitySvc facilitySvc;

    private static UUID facilityId = UUID.randomUUID();

    @Test
    void findAllTest() {
        Mockito.doReturn(getTestFacilities()).when(facilityRepository)
                .findAll();
        Iterable<Facility> facilities = facilitySvc.findAll();
        assertEquals(Iterables.size(facilities), 1);
    }

    @Test
    void getTest() {
        Mockito.doReturn(Optional.of(getTestFacility()))
                .when(facilityRepository).findById(facilityId);
        Facility facility = facilitySvc.get(facilityId)
            .orElse(null);
        assertEquals(facility.getId(), facilityId);
    }

    @Test
    void saveTest() {
        facilitySvc.save(getTestFacility());
    }

    @Test
    void saveAllTest() {
        facilitySvc.saveAll(getTestFacilities());
    }

    @Test
    void deleteTest() {
        Mockito.doReturn(true).when(facilityRepository)
                .existsById(facilityId);
        facilitySvc.delete(facilityId);
    }

    @Test
    void deleteAllTest() {
        facilitySvc.deleteAll();
    }

    @Test
    void updateTest() {
        Mockito.doReturn(true).when(facilityRepository)
                .existsById(facilityId);
        facilitySvc.update(getTestFacility());
    }

    @Test
    void getIdTest() {
        assertEquals(facilitySvc.getId(getTestFacility()), facilityId);
    }

    @Test
    void getRepository() {
        assertEquals(facilitySvc.getRepository(), facilityRepository);
    }

    private Facility getTestFacility() {
        Facility facility = new Facility();
        facility.setId(facilityId);
        return facility;
    }

    private Iterable<Facility> getTestFacilities() {
        ArrayList<Facility> facilities = new ArrayList<Facility>();
        facilities.add(getTestFacility());
        return facilities;
   }

}
