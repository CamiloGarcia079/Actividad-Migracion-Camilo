package com.mindconnect.infrastructure.encountermodality.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.encountermodality.adapters.out.persistence.entity.EncounterModalityJpaEntity;

/**
 * Repositorio de Spring Data para encounter_modalities. Spring genera la implementación.
 */
public interface SpringDataEncounterModalityJpaRepository extends JpaRepository<EncounterModalityJpaEntity, UUID> {
}
