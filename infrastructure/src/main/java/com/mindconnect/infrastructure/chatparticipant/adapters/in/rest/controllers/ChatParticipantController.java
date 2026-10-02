package com.mindconnect.infrastructure.chatparticipant.adapters.in.rest.controllers;

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

import com.mindconnect.application.chatparticipant.ports.in.ChatParticipantUseCase;
import com.mindconnect.infrastructure.chatparticipant.adapters.in.rest.dtos.ChatParticipantRequest;
import com.mindconnect.infrastructure.chatparticipant.adapters.in.rest.dtos.ChatParticipantResponse;

/**
 * Adaptador de entrada REST para chat_participants.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/chat-participants")
public class ChatParticipantController {

    private final ChatParticipantUseCase useCase;

    public ChatParticipantController(ChatParticipantUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ChatParticipantResponse> create(@Valid @RequestBody ChatParticipantRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ChatParticipantResponse.from(created));
    }

    @GetMapping
    public List<ChatParticipantResponse> getAll() {
        return useCase.getAll().stream().map(ChatParticipantResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ChatParticipantResponse getById(@PathVariable UUID id) {
        return ChatParticipantResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ChatParticipantResponse update(@PathVariable UUID id, @Valid @RequestBody ChatParticipantRequest request) {
        return ChatParticipantResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
