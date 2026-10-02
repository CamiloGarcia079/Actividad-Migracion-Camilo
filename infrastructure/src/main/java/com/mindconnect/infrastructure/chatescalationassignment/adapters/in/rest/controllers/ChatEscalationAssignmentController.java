package com.mindconnect.infrastructure.chatescalationassignment.adapters.in.rest.controllers;

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

import com.mindconnect.application.chatescalationassignment.ports.in.ChatEscalationAssignmentUseCase;
import com.mindconnect.infrastructure.chatescalationassignment.adapters.in.rest.dtos.ChatEscalationAssignmentRequest;
import com.mindconnect.infrastructure.chatescalationassignment.adapters.in.rest.dtos.ChatEscalationAssignmentResponse;

/**
 * Adaptador de entrada REST para chat_escalation_assignments.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/chat-escalation-assignments")
public class ChatEscalationAssignmentController {

    private final ChatEscalationAssignmentUseCase useCase;

    public ChatEscalationAssignmentController(ChatEscalationAssignmentUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ChatEscalationAssignmentResponse> create(@Valid @RequestBody ChatEscalationAssignmentRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ChatEscalationAssignmentResponse.from(created));
    }

    @GetMapping
    public List<ChatEscalationAssignmentResponse> getAll() {
        return useCase.getAll().stream().map(ChatEscalationAssignmentResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ChatEscalationAssignmentResponse getById(@PathVariable UUID id) {
        return ChatEscalationAssignmentResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ChatEscalationAssignmentResponse update(@PathVariable UUID id, @Valid @RequestBody ChatEscalationAssignmentRequest request) {
        return ChatEscalationAssignmentResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
