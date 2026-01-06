package com.deloitte.elrr.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.PersonalCompetency;

@Repository
public interface PersonalCompetencyRepository extends JpaRepository<
        PersonalCompetency, UUID> {

    /**
     * @param personId
     * @param competencyId
     * @return PeronalCompetency
     */
    PersonalCompetency findByPersonIdAndCompetencyId(UUID personId,
            UUID competencyId);
}
