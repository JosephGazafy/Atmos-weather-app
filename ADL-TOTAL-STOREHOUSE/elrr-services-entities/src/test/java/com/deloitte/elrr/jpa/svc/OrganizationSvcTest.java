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

import com.deloitte.elrr.entity.Organization;
import com.deloitte.elrr.repository.OrganizationRepository;
import com.google.common.collect.Iterables;

@ExtendWith(MockitoExtension.class)
public class OrganizationSvcTest {

    @Mock
    private OrganizationRepository organizationRepository;

    @InjectMocks
    private OrganizationSvc organizationSvc;

    private static UUID organizationId = UUID.randomUUID();

    @Test
    void findAllTest() {
        Mockito.doReturn(getTestOrganizations()).when(organizationRepository)
                .findAll();
        Iterable<Organization> organizations = organizationSvc.findAll();
        assertEquals(Iterables.size(organizations), 1);
    }

    @Test
    void getTest() {
        Mockito.doReturn(Optional.of(getTestOrganization()))
                .when(organizationRepository).findById(organizationId);
        Organization organization = organizationSvc.get(organizationId)
            .orElse(null);
        assertEquals(organization.getId(), organizationId);
    }

    @Test
    void saveTest() {
        organizationSvc.save(getTestOrganization());
    }

    @Test
    void saveAllTest() {
        organizationSvc.saveAll(getTestOrganizations());
    }

    @Test
    void deleteTest() {
        Mockito.doReturn(true).when(organizationRepository)
                .existsById(organizationId);
        organizationSvc.delete(organizationId);
    }

    @Test
    void deleteAllTest() {
        organizationSvc.deleteAll();
    }

    @Test
    void updateTest() {
        Mockito.doReturn(true).when(organizationRepository)
                .existsById(organizationId);
        organizationSvc.update(getTestOrganization());
    }

    @Test
    void getIdTest() {
        assertEquals(organizationSvc.getId(getTestOrganization()),
            organizationId);
    }

    @Test
    void getRepository() {
        assertEquals(organizationSvc.getRepository(), organizationRepository);
    }

    private Organization getTestOrganization() {
        Organization organization = new Organization();
        organization.setId(organizationId);
        return organization;
    }

    private Iterable<Organization> getTestOrganizations() {
        ArrayList<Organization> organizations = new ArrayList<Organization>();
        organizations.add(getTestOrganization());
        return organizations;
   }

}
