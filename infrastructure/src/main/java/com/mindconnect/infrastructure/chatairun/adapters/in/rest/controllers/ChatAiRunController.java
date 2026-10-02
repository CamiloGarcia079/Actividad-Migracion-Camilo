package com.mindconnect.infrastructure.chatairun.adapters.in.rest.controllers;

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

import com.mindconnect.application.chatairun.ports.in.ChatAiRunUseCase;
import com.mindconnect.infrastructure.chatairun.adapters.in.rest.dtos.ChatAiRunRequest;
import com.mindconnect.infrastructure.chatairun.adapters.in.rest.dtos.ChatAiRunResponse;

/**
 * Adaptador de entrada REST para chat_ai_runs.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/chat-ai-runs")
public class ChatAiRunController {

    private final ChatAiRunUseCase useCase;

    public ChatAiRunController(ChatAiRunUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ChatAiRunResponse> create(@Valid @RequestBody ChatAiRunRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ChatAiRunResponse.from(created));
    }

    @GetMapping
    public List<ChatAiRunResponse> getAll() {
        return useCase.getAll().stream().map(ChatAiRunResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ChatAiRunResponse getById(@PathVariable UUID id) {
        return ChatAiRunResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ChatAiRunResponse update(@PathVariable UUID id, @Valid @RequestBody ChatAiRunRequest request) {
        return ChatAiRunResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
