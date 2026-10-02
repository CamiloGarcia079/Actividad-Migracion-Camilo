package com.mindconnect.infrastructure.chatairunmetric.adapters.in.rest.controllers;

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

import com.mindconnect.application.chatairunmetric.ports.in.ChatAiRunMetricUseCase;
import com.mindconnect.infrastructure.chatairunmetric.adapters.in.rest.dtos.ChatAiRunMetricRequest;
import com.mindconnect.infrastructure.chatairunmetric.adapters.in.rest.dtos.ChatAiRunMetricResponse;

/**
 * Adaptador de entrada REST para chat_ai_run_metrics.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/chat-ai-run-metrics")
public class ChatAiRunMetricController {

    private final ChatAiRunMetricUseCase useCase;

    public ChatAiRunMetricController(ChatAiRunMetricUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ChatAiRunMetricResponse> create(@Valid @RequestBody ChatAiRunMetricRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ChatAiRunMetricResponse.from(created));
    }

    @GetMapping
    public List<ChatAiRunMetricResponse> getAll() {
        return useCase.getAll().stream().map(ChatAiRunMetricResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ChatAiRunMetricResponse getById(@PathVariable UUID id) {
        return ChatAiRunMetricResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ChatAiRunMetricResponse update(@PathVariable UUID id, @Valid @RequestBody ChatAiRunMetricRequest request) {
        return ChatAiRunMetricResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
