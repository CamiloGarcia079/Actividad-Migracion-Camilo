package com.mindconnect.infrastructure.chatairunerror.adapters.in.rest.controllers;

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

import com.mindconnect.application.chatairunerror.ports.in.ChatAiRunErrorUseCase;
import com.mindconnect.infrastructure.chatairunerror.adapters.in.rest.dtos.ChatAiRunErrorRequest;
import com.mindconnect.infrastructure.chatairunerror.adapters.in.rest.dtos.ChatAiRunErrorResponse;

/**
 * Adaptador de entrada REST para chat_ai_run_errors.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/chat-ai-run-errors")
public class ChatAiRunErrorController {

    private final ChatAiRunErrorUseCase useCase;

    public ChatAiRunErrorController(ChatAiRunErrorUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ChatAiRunErrorResponse> create(@Valid @RequestBody ChatAiRunErrorRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ChatAiRunErrorResponse.from(created));
    }

    @GetMapping
    public List<ChatAiRunErrorResponse> getAll() {
        return useCase.getAll().stream().map(ChatAiRunErrorResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ChatAiRunErrorResponse getById(@PathVariable UUID id) {
        return ChatAiRunErrorResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ChatAiRunErrorResponse update(@PathVariable UUID id, @Valid @RequestBody ChatAiRunErrorRequest request) {
        return ChatAiRunErrorResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
