package com.mindconnect.application.riskassessment.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.riskassessment.dtos.RiskAssessmentCommand;
import com.mindconnect.domain.riskassessment.model.aggregates.RiskAssessment;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre risk_assessments.
 */
public interface RiskAssessmentUseCase {

    RiskAssessment create(RiskAssessmentCommand command);

    RiskAssessment getById(UUID id);

    List<RiskAssessment> getAll();

    RiskAssessment update(UUID id, RiskAssessmentCommand command);

    void delete(UUID id);
}
