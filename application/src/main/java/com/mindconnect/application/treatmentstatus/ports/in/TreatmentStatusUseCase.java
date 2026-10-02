package com.mindconnect.application.treatmentstatus.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.treatmentstatus.dtos.TreatmentStatusCommand;
import com.mindconnect.domain.treatmentstatus.model.aggregates.TreatmentStatus;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre treatment_statuses.
 */
public interface TreatmentStatusUseCase {

    TreatmentStatus create(TreatmentStatusCommand command);

    TreatmentStatus getById(UUID id);

    List<TreatmentStatus> getAll();

    TreatmentStatus update(UUID id, TreatmentStatusCommand command);

    void delete(UUID id);
}
