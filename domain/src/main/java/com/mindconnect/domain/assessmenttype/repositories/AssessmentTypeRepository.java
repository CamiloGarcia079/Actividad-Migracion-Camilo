package com.mindconnect.domain.assessmenttype.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.assessmenttype.model.aggregates.AssessmentType;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar assessment_types.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface AssessmentTypeRepository {

    AssessmentType save(AssessmentType assessmentType);

    Optional<AssessmentType> findById(UUID id);

    List<AssessmentType> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
