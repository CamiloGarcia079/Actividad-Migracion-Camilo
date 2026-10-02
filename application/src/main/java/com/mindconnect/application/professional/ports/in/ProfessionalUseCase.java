package com.mindconnect.application.professional.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.professional.dtos.ProfessionalCommand;
import com.mindconnect.domain.professional.model.aggregates.Professional;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre professionals.
 */
public interface ProfessionalUseCase {

    Professional create(ProfessionalCommand command);

    Professional getById(UUID id);

    List<Professional> getAll();

    Professional update(UUID id, ProfessionalCommand command);

    void delete(UUID id);
}
