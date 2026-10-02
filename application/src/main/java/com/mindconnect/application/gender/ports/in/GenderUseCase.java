package com.mindconnect.application.gender.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.gender.dtos.GenderCommand;
import com.mindconnect.domain.gender.model.aggregates.Gender;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre genders.
 */
public interface GenderUseCase {

    Gender create(GenderCommand command);

    Gender getById(UUID id);

    List<Gender> getAll();

    Gender update(UUID id, GenderCommand command);

    void delete(UUID id);
}
