package com.mindconnect.domain.professionalstudy.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.professionalstudy.model.aggregates.ProfessionalStudy;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar professional_studies.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ProfessionalStudyRepository {

    ProfessionalStudy save(ProfessionalStudy professionalStudy);

    Optional<ProfessionalStudy> findById(UUID id);

    List<ProfessionalStudy> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
