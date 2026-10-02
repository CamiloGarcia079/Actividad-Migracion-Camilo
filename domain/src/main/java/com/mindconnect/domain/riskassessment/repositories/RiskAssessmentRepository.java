package com.mindconnect.domain.riskassessment.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.riskassessment.model.aggregates.RiskAssessment;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar risk_assessments.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface RiskAssessmentRepository {

    RiskAssessment save(RiskAssessment riskAssessment);

    Optional<RiskAssessment> findById(UUID id);

    List<RiskAssessment> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
