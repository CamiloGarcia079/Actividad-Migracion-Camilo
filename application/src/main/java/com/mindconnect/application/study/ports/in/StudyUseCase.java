package com.mindconnect.application.study.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.study.dtos.StudyCommand;
import com.mindconnect.domain.study.model.aggregates.Study;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre studies.
 */
public interface StudyUseCase {

    Study create(StudyCommand command);

    Study getById(UUID id);

    List<Study> getAll();

    Study update(UUID id, StudyCommand command);

    void delete(UUID id);
}
