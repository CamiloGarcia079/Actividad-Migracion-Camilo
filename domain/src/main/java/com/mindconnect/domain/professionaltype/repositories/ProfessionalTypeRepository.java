package com.mindconnect.domain.professionaltype.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.professionaltype.model.aggregates.ProfessionalType;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar professional_types.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ProfessionalTypeRepository {

    ProfessionalType save(ProfessionalType professionalType);

    Optional<ProfessionalType> findById(UUID id);

    List<ProfessionalType> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
