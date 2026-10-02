package com.mindconnect.infrastructure.chatescalation.adapters.in.rest.controllers;

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

import com.mindconnect.application.chatescalation.ports.in.ChatEscalationUseCase;
import com.mindconnect.infrastructure.chatescalation.adapters.in.rest.dtos.ChatEscalationRequest;
import com.mindconnect.infrastructure.chatescalation.adapters.in.rest.dtos.ChatEscalationResponse;

/**
 * Adaptador de entrada REST para chat_escalations.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/chat-escalations")
public class ChatEscalationController {

    private final ChatEscalationUseCase useCase;

    public ChatEscalationController(ChatEscalationUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ChatEscalationResponse> create(@Valid @RequestBody ChatEscalationRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ChatEscalationResponse.from(created));
    }

    @GetMapping
    public List<ChatEscalationResponse> getAll() {
        return useCase.getAll().stream().map(ChatEscalationResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ChatEscalationResponse getById(@PathVariable UUID id) {
        return ChatEscalationResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ChatEscalationResponse update(@PathVariable UUID id, @Valid @RequestBody ChatEscalationRequest request) {
        return ChatEscalationResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
