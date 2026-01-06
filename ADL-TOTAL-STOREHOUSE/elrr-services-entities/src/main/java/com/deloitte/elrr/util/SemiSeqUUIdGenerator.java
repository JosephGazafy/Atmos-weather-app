package com.deloitte.elrr.util;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.fasterxml.uuid.Generators;

import com.deloitte.elrr.entity.Entity;

public class SemiSeqUUIdGenerator implements IdentifierGenerator {

    @Override
    public Object generate(final SharedSessionContractImplementor session,
                    final Object object) {
        // do a checked cast on Object to Entity and check if it already
        // has an ID. If it does, return that ID
        if (object instanceof Entity) {
            Entity entity = (Entity) object;
            if (entity.getId() != null) {
                return entity.getId();
            }
        }
        return Generators.timeBasedEpochRandomGenerator().generate();
    }
}
