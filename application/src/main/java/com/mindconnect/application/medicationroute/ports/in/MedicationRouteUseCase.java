package com.mindconnect.application.medicationroute.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.medicationroute.dtos.MedicationRouteCommand;
import com.mindconnect.domain.medicationroute.model.aggregates.MedicationRoute;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre medication_routes.
 */
public interface MedicationRouteUseCase {

    MedicationRoute create(MedicationRouteCommand command);

    MedicationRoute getById(UUID id);

    List<MedicationRoute> getAll();

    MedicationRoute update(UUID id, MedicationRouteCommand command);

    void delete(UUID id);
}
