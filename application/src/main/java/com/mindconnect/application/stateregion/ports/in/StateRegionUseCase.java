package com.mindconnect.application.stateregion.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.stateregion.dtos.StateRegionCommand;
import com.mindconnect.domain.stateregion.model.aggregates.StateRegion;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre state_regions.
 */
public interface StateRegionUseCase {

    StateRegion create(StateRegionCommand command);

    StateRegion getById(UUID id);

    List<StateRegion> getAll();

    StateRegion update(UUID id, StateRegionCommand command);

    void delete(UUID id);
}
