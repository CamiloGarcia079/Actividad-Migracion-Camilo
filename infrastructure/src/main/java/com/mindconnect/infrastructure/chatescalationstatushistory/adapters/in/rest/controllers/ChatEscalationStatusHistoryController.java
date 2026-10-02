package com.mindconnect.infrastructure.chatescalationstatushistory.adapters.in.rest.controllers;

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

import com.mindconnect.application.chatescalationstatushistory.ports.in.ChatEscalationStatusHistoryUseCase;
import com.mindconnect.infrastructure.chatescalationstatushistory.adapters.in.rest.dtos.ChatEscalationStatusHistoryRequest;
import com.mindconnect.infrastructure.chatescalationstatushistory.adapters.in.rest.dtos.ChatEscalationStatusHistoryResponse;

/**
 * Adaptador de entrada REST para chat_escalation_status_history.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/chat-escalation-status-history")
public class ChatEscalationStatusHistoryController {

    private final ChatEscalationStatusHistoryUseCase useCase;

    public ChatEscalationStatusHistoryController(ChatEscalationStatusHistoryUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ChatEscalationStatusHistoryResponse> create(@Valid @RequestBody ChatEscalationStatusHistoryRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ChatEscalationStatusHistoryResponse.from(created));
    }

    @GetMapping
    public List<ChatEscalationStatusHistoryResponse> getAll() {
        return useCase.getAll().stream().map(ChatEscalationStatusHistoryResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ChatEscalationStatusHistoryResponse getById(@PathVariable UUID id) {
        return ChatEscalationStatusHistoryResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ChatEscalationStatusHistoryResponse update(@PathVariable UUID id, @Valid @RequestBody ChatEscalationStatusHistoryRequest request) {
        return ChatEscalationStatusHistoryResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
