package com.mindconnect.application.mentalstatusexam.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.mentalstatusexam.dtos.MentalStatusExamCommand;
import com.mindconnect.application.mentalstatusexam.exceptions.MentalStatusExamNotFoundException;
import com.mindconnect.application.mentalstatusexam.ports.in.MentalStatusExamUseCase;
import com.mindconnect.domain.mentalstatusexam.model.aggregates.MentalStatusExam;
import com.mindconnect.domain.mentalstatusexam.repositories.MentalStatusExamRepository;

/**
 * Implementación de los casos de uso de mental_status_exams.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class MentalStatusExamService implements MentalStatusExamUseCase {

    private final MentalStatusExamRepository repository;

    public MentalStatusExamService(MentalStatusExamRepository repository) {
        this.repository = repository;
    }

    @Override
    public MentalStatusExam create(MentalStatusExamCommand command) {
        LocalDateTime now = LocalDateTime.now();
        MentalStatusExam mentalStatusExam = new MentalStatusExam(
                UUID.randomUUID(),
                command.encounterId(),
                command.appearance(),
                command.behavior(),
                command.attitude(),
                command.consciousness(),
                command.orientation(),
                command.attention(),
                command.memory(),
                command.speech(),
                command.mood(),
                command.affect(),
                command.thoughtProcess(),
                command.thoughtContent(),
                command.perception(),
                command.judgment(),
                command.insight(),
                command.psychomotorActivity(),
                command.observations(),
                now,
                command.createdBy());
        return repository.save(mentalStatusExam);
    }

    @Override
    public MentalStatusExam getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new MentalStatusExamNotFoundException(id));
    }

    @Override
    public List<MentalStatusExam> getAll() {
        return repository.findAll();
    }

    @Override
    public MentalStatusExam update(UUID id, MentalStatusExamCommand command) {
        MentalStatusExam existing = getById(id);
        MentalStatusExam updated = new MentalStatusExam(
                existing.id(),
                command.encounterId(),
                command.appearance(),
                command.behavior(),
                command.attitude(),
                command.consciousness(),
                command.orientation(),
                command.attention(),
                command.memory(),
                command.speech(),
                command.mood(),
                command.affect(),
                command.thoughtProcess(),
                command.thoughtContent(),
                command.perception(),
                command.judgment(),
                command.insight(),
                command.psychomotorActivity(),
                command.observations(),
                existing.createdAt(),
                command.createdBy());
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new MentalStatusExamNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
