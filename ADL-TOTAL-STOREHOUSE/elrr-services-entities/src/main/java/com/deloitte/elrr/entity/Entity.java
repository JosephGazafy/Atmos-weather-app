package com.deloitte.elrr.entity;

import java.util.UUID;

import com.deloitte.elrr.util.SemiSeqId;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Entity {

    @Id
    @SemiSeqId
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected UUID id;

    @Getter
    @Setter
    public abstract static class Filter {
        private UUID[] id;
    }
}
