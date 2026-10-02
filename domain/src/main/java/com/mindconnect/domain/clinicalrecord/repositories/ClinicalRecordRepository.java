package com.mindconnect.domain.clinicalrecord.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.clinicalrecord.model.aggregates.ClinicalRecord;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar clinical_records.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ClinicalRecordRepository {

    ClinicalRecord save(ClinicalRecord clinicalRecord);

    Optional<ClinicalRecord> findById(UUID id);

    List<ClinicalRecord> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
