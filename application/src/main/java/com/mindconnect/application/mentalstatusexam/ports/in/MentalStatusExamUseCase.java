package com.mindconnect.application.mentalstatusexam.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.mentalstatusexam.dtos.MentalStatusExamCommand;
import com.mindconnect.domain.mentalstatusexam.model.aggregates.MentalStatusExam;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre mental_status_exams.
 */
public interface MentalStatusExamUseCase {

    MentalStatusExam create(MentalStatusExamCommand command);

    MentalStatusExam getById(UUID id);

    List<MentalStatusExam> getAll();

    MentalStatusExam update(UUID id, MentalStatusExamCommand command);

    void delete(UUID id);
}
