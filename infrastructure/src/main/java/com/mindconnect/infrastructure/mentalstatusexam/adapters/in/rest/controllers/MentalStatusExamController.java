package com.mindconnect.infrastructure.mentalstatusexam.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindconnect.application.mentalstatusexam.ports.in.MentalStatusExamUseCase;
import com.mindconnect.infrastructure.mentalstatusexam.adapters.in.rest.dtos.MentalStatusExamRequest;
import com.mindconnect.infrastructure.mentalstatusexam.adapters.in.rest.dtos.MentalStatusExamResponse;

/**
 * Adaptador de entrada REST para mental_status_exams.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/mental-status-exams")
public class MentalStatusExamController {

    private final MentalStatusExamUseCase useCase;

    public MentalStatusExamController(MentalStatusExamUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<MentalStatusExamResponse> create(@Valid @RequestBody MentalStatusExamRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(MentalStatusExamResponse.from(created));
    }

    @GetMapping
    public List<MentalStatusExamResponse> getAll() {
        return useCase.getAll().stream().map(MentalStatusExamResponse::from).toList();
    }

    @GetMapping("/{id}")
    public MentalStatusExamResponse getById(@PathVariable UUID id) {
        return MentalStatusExamResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public MentalStatusExamResponse update(@PathVariable UUID id, @Valid @RequestBody MentalStatusExamRequest request) {
        return MentalStatusExamResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
