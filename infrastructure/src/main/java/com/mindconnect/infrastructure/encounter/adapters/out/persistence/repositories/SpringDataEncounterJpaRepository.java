package com.mindconnect.infrastructure.encounter.adapters.out.persistence.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindconnect.infrastructure.encounter.adapters.out.persistence.entity.EncounterJpaEntity;

/**
 * Repositorio de Spring Data para encounters. Spring genera la implementación.
 */
public interface SpringDataEncounterJpaRepository extends JpaRepository<EncounterJpaEntity, UUID> {
}
