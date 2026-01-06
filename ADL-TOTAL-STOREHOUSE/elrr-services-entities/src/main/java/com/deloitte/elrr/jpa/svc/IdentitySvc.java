package com.deloitte.elrr.jpa.svc;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Identity;
import com.deloitte.elrr.repository.IdentityRepository;

@Service
public class IdentitySvc implements CommonSvc<Identity, UUID> {

    private final IdentityRepository identityRepository;

    /**
     *
     * @param argsIdentityRepository
     */
    public IdentitySvc(final IdentityRepository argsIdentityRepository) {
        this.identityRepository = argsIdentityRepository;
    }

    @Override
    public CrudRepository<Identity, UUID> getRepository() {
        return this.identityRepository;
    }

    @Override
    public UUID getId(final Identity identity) {
        return identity.getId();
    }

    @Override
    public Identity save(final Identity identity) {
        return CommonSvc.super.save(identity);
    }

    /**
    * Search for Identity by IFI.
    *
    * @param   ifi  Inverse functional identifier to search with
    * @return  Matching Identity
    */
    public Identity getByIfi(final String ifi) {
        return identityRepository.findByIfi(ifi);
    }

}
