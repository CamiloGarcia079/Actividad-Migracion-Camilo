package com.mindconnect.application.clinicalrecordstatus.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.clinicalrecordstatus.dtos.ClinicalRecordStatusCommand;
import com.mindconnect.domain.clinicalrecordstatus.model.aggregates.ClinicalRecordStatus;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre clinical_record_statuses.
 */
public interface ClinicalRecordStatusUseCase {

    ClinicalRecordStatus create(ClinicalRecordStatusCommand command);

    ClinicalRecordStatus getById(UUID id);

    List<ClinicalRecordStatus> getAll();

    ClinicalRecordStatus update(UUID id, ClinicalRecordStatusCommand command);

    void delete(UUID id);
}
