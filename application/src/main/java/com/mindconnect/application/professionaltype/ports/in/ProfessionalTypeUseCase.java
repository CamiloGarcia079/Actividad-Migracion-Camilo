package com.mindconnect.application.professionaltype.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.professionaltype.dtos.ProfessionalTypeCommand;
import com.mindconnect.domain.professionaltype.model.aggregates.ProfessionalType;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre professional_types.
 */
public interface ProfessionalTypeUseCase {

    ProfessionalType create(ProfessionalTypeCommand command);

    ProfessionalType getById(UUID id);

    List<ProfessionalType> getAll();

    ProfessionalType update(UUID id, ProfessionalTypeCommand command);

    void delete(UUID id);
}
