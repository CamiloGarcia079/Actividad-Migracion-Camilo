package com.mindconnect.domain.medicationroute.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.medicationroute.model.aggregates.MedicationRoute;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar medication_routes.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface MedicationRouteRepository {

    MedicationRoute save(MedicationRoute medicationRoute);

    Optional<MedicationRoute> findById(UUID id);

    List<MedicationRoute> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
