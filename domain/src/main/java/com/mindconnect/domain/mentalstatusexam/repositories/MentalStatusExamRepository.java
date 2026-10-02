package com.mindconnect.domain.mentalstatusexam.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.mentalstatusexam.model.aggregates.MentalStatusExam;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar mental_status_exams.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface MentalStatusExamRepository {

    MentalStatusExam save(MentalStatusExam mentalStatusExam);

    Optional<MentalStatusExam> findById(UUID id);

    List<MentalStatusExam> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
