package com.mindconnect.infrastructure.conversationstatus.adapters.in.rest.controllers;

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

import com.mindconnect.application.conversationstatus.ports.in.ConversationStatusUseCase;
import com.mindconnect.infrastructure.conversationstatus.adapters.in.rest.dtos.ConversationStatusRequest;
import com.mindconnect.infrastructure.conversationstatus.adapters.in.rest.dtos.ConversationStatusResponse;

/**
 * Adaptador de entrada REST para conversations_statuses.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/conversations-statuses")
public class ConversationStatusController {

    private final ConversationStatusUseCase useCase;

    public ConversationStatusController(ConversationStatusUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ConversationStatusResponse> create(@Valid @RequestBody ConversationStatusRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ConversationStatusResponse.from(created));
    }

    @GetMapping
    public List<ConversationStatusResponse> getAll() {
        return useCase.getAll().stream().map(ConversationStatusResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ConversationStatusResponse getById(@PathVariable UUID id) {
        return ConversationStatusResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ConversationStatusResponse update(@PathVariable UUID id, @Valid @RequestBody ConversationStatusRequest request) {
        return ConversationStatusResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
