package com.mindconnect.domain.risklevel.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.risklevel.model.aggregates.RiskLevel;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar risk_levels.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface RiskLevelRepository {

    RiskLevel save(RiskLevel riskLevel);

    Optional<RiskLevel> findById(UUID id);

    List<RiskLevel> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
