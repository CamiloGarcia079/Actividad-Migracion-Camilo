package com.mindconnect.application.assessmenttype.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.assessmenttype.dtos.AssessmentTypeCommand;
import com.mindconnect.domain.assessmenttype.model.aggregates.AssessmentType;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre assessment_types.
 */
public interface AssessmentTypeUseCase {

    AssessmentType create(AssessmentTypeCommand command);

    AssessmentType getById(UUID id);

    List<AssessmentType> getAll();

    AssessmentType update(UUID id, AssessmentTypeCommand command);

    void delete(UUID id);
}
