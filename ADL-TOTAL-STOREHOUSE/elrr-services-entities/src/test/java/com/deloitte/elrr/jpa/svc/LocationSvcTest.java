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

import com.deloitte.elrr.entity.Location;
import com.deloitte.elrr.repository.LocationRepository;
import com.google.common.collect.Iterables;

@ExtendWith(MockitoExtension.class)
public class LocationSvcTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationSvc locationSvc;

    private static UUID locationId = UUID.randomUUID();

    @Test
    void findAllTest() {
        Mockito.doReturn(getTestLocations()).when(locationRepository)
                .findAll();
        Iterable<Location> locations = locationSvc.findAll();
        assertEquals(Iterables.size(locations), 1);
    }

    @Test
    void getTest() {
        Mockito.doReturn(Optional.of(getTestLocation()))
                .when(locationRepository).findById(locationId);
        Location location = locationSvc.get(locationId)
            .orElse(null);
        assertEquals(location.getId(), locationId);
    }

    @Test
    void saveTest() {
        locationSvc.save(getTestLocation());
    }

    @Test
    void saveAllTest() {
        locationSvc.saveAll(getTestLocations());
    }

    @Test
    void deleteTest() {
        Mockito.doReturn(true).when(locationRepository)
                .existsById(locationId);
        locationSvc.delete(locationId);
    }

    @Test
    void deleteAllTest() {
        locationSvc.deleteAll();
    }

    @Test
    void updateTest() {
        Mockito.doReturn(true).when(locationRepository)
                .existsById(locationId);
        locationSvc.update(getTestLocation());
    }

    @Test
    void getIdTest() {
        assertEquals(locationSvc.getId(getTestLocation()), locationId);
    }

    @Test
    void getRepository() {
        assertEquals(locationSvc.getRepository(), locationRepository);
    }

    private Location getTestLocation() {
        Location location = new Location();
        location.setId(locationId);
        return location;
    }

    private Iterable<Location> getTestLocations() {
        ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(getTestLocation());
        return locations;
   }

}
