package com.deloitte.elrr.jpa.svc;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.PersonalCredential;
import com.deloitte.elrr.repository.PersonalCredentialRepository;

@Service
public class PersonalCredentialSvc implements CommonSvc<PersonalCredential,
        UUID> {

    private final PersonalCredentialRepository personalCredentialRepository;

    /**
     * @param argsRepository
     */
    public PersonalCredentialSvc(
            final PersonalCredentialRepository argsRepository) {
        this.personalCredentialRepository = argsRepository;
    }

    @Override
    public CrudRepository<PersonalCredential, UUID> getRepository() {
        return this.personalCredentialRepository;
    }

    @Override
    public UUID getId(final PersonalCredential pq) {
        return pq.getId();
    }

    @Override
    public PersonalCredential save(final PersonalCredential pq) {
        return CommonSvc.super.save(pq);
    }

    /**
     * @param personId
     * @param credentialId
     * @return PersonalCredential
     */
    public PersonalCredential findByPersonIdAndCredentialId(UUID personId,
            UUID credentialId) {
        PersonalCredential personalCredential = personalCredentialRepository
                .findByPersonIdAndCredentialId(personId, credentialId);
        return personalCredential;
    }
}
