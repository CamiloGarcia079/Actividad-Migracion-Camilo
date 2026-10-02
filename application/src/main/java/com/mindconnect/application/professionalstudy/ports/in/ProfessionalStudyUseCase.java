package com.mindconnect.application.professionalstudy.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.professionalstudy.dtos.ProfessionalStudyCommand;
import com.mindconnect.domain.professionalstudy.model.aggregates.ProfessionalStudy;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre professional_studies.
 */
public interface ProfessionalStudyUseCase {

    ProfessionalStudy create(ProfessionalStudyCommand command);

    ProfessionalStudy getById(UUID id);

    List<ProfessionalStudy> getAll();

    ProfessionalStudy update(UUID id, ProfessionalStudyCommand command);

    void delete(UUID id);
}
