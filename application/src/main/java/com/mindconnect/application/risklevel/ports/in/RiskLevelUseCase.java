package com.mindconnect.application.risklevel.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.risklevel.dtos.RiskLevelCommand;
import com.mindconnect.domain.risklevel.model.aggregates.RiskLevel;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre risk_levels.
 */
public interface RiskLevelUseCase {

    RiskLevel create(RiskLevelCommand command);

    RiskLevel getById(UUID id);

    List<RiskLevel> getAll();

    RiskLevel update(UUID id, RiskLevelCommand command);

    void delete(UUID id);
}
