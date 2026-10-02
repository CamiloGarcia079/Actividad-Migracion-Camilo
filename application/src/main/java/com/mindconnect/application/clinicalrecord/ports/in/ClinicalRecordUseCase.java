package com.mindconnect.application.clinicalrecord.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.clinicalrecord.dtos.ClinicalRecordCommand;
import com.mindconnect.domain.clinicalrecord.model.aggregates.ClinicalRecord;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre clinical_records.
 */
public interface ClinicalRecordUseCase {

    ClinicalRecord create(ClinicalRecordCommand command);

    ClinicalRecord getById(UUID id);

    List<ClinicalRecord> getAll();

    ClinicalRecord update(UUID id, ClinicalRecordCommand command);

    void delete(UUID id);
}
