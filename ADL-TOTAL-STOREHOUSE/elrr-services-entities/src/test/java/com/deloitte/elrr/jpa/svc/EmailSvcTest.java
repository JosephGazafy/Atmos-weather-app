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

import com.deloitte.elrr.entity.Email;
import com.deloitte.elrr.repository.EmailRepository;
import com.google.common.collect.Iterables;

@ExtendWith(MockitoExtension.class)
public class EmailSvcTest {

    @Mock
    private EmailRepository emailRepository;

    @InjectMocks
    private EmailSvc emailSvc;

    private static UUID emailId = UUID.randomUUID();

    @Test
    void findAllTest() {
        Mockito.doReturn(getTestEmails()).when(emailRepository)
                .findAll();
        Iterable<Email> emails = emailSvc.findAll();
        assertEquals(Iterables.size(emails), 1);
    }

    @Test
    void getTest() {
        Mockito.doReturn(Optional.of(getTestEmail()))
                .when(emailRepository).findById(emailId);
        Email email = emailSvc.get(emailId)
            .orElse(null);
        assertEquals(email.getId(), emailId);
    }

    @Test
    void saveTest() {
        emailSvc.save(getTestEmail());
    }

    @Test
    void saveAllTest() {
        emailSvc.saveAll(getTestEmails());
    }

    @Test
    void deleteTest() {
        Mockito.doReturn(true).when(emailRepository)
                .existsById(emailId);
        emailSvc.delete(emailId);
    }

    @Test
    void deleteAllTest() {
        emailSvc.deleteAll();
    }

    @Test
    void updateTest() {
        Mockito.doReturn(true).when(emailRepository)
                .existsById(emailId);
        emailSvc.update(getTestEmail());
    }

    @Test
    void getIdTest() {
        assertEquals(emailSvc.getId(getTestEmail()), emailId);
    }

    @Test
    void getRepository() {
        assertEquals(emailSvc.getRepository(), emailRepository);
    }

    private Email getTestEmail() {
        Email email = new Email();
        email.setId(emailId);
        return email;
    }

    private Iterable<Email> getTestEmails() {
        ArrayList<Email> emails = new ArrayList<Email>();
        emails.add(getTestEmail());
        return emails;
   }

}
