package com.mindconnect.domain.clinicalrecordstatus.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.clinicalrecordstatus.model.aggregates.ClinicalRecordStatus;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar clinical_record_statuses.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ClinicalRecordStatusRepository {

    ClinicalRecordStatus save(ClinicalRecordStatus clinicalRecordStatus);

    Optional<ClinicalRecordStatus> findById(UUID id);

    List<ClinicalRecordStatus> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
