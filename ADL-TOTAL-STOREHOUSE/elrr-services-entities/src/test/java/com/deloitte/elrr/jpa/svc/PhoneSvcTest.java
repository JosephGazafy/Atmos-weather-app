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

import com.deloitte.elrr.entity.Phone;
import com.deloitte.elrr.repository.PhoneRepository;
import com.google.common.collect.Iterables;

@ExtendWith(MockitoExtension.class)
public class PhoneSvcTest {

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private PhoneSvc phoneSvc;

    private static UUID phoneId = UUID.randomUUID();

    @Test
    void findAllTest() {
        Mockito.doReturn(getTestPhones()).when(phoneRepository)
                .findAll();
        Iterable<Phone> phones = phoneSvc.findAll();
        assertEquals(Iterables.size(phones), 1);
    }

    @Test
    void getTest() {
        Mockito.doReturn(Optional.of(getTestPhone()))
                .when(phoneRepository).findById(phoneId);
        Phone phone = phoneSvc.get(phoneId)
            .orElse(null);
        assertEquals(phone.getId(), phoneId);
    }

    @Test
    void saveTest() {
        phoneSvc.save(getTestPhone());
    }

    @Test
    void saveAllTest() {
        phoneSvc.saveAll(getTestPhones());
    }

    @Test
    void deleteTest() {
        Mockito.doReturn(true).when(phoneRepository)
                .existsById(phoneId);
        phoneSvc.delete(phoneId);
    }

    @Test
    void deleteAllTest() {
        phoneSvc.deleteAll();
    }

    @Test
    void updateTest() {
        Mockito.doReturn(true).when(phoneRepository)
                .existsById(phoneId);
        phoneSvc.update(getTestPhone());
    }

    @Test
    void getIdTest() {
        assertEquals(phoneSvc.getId(getTestPhone()), phoneId);
    }

    @Test
    void getRepository() {
        assertEquals(phoneSvc.getRepository(), phoneRepository);
    }

    private Phone getTestPhone() {
        Phone phone = new Phone();
        phone.setId(phoneId);
        return phone;
    }

    private Iterable<Phone> getTestPhones() {
        ArrayList<Phone> phones = new ArrayList<Phone>();
        phones.add(getTestPhone());
        return phones;
   }

}
