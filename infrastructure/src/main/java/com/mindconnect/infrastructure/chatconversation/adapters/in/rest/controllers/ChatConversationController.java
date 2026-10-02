package com.mindconnect.infrastructure.chatconversation.adapters.in.rest.controllers;

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

import com.mindconnect.application.chatconversation.ports.in.ChatConversationUseCase;
import com.mindconnect.infrastructure.chatconversation.adapters.in.rest.dtos.ChatConversationRequest;
import com.mindconnect.infrastructure.chatconversation.adapters.in.rest.dtos.ChatConversationResponse;

/**
 * Adaptador de entrada REST para chat_conversations.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/chat-conversations")
public class ChatConversationController {

    private final ChatConversationUseCase useCase;

    public ChatConversationController(ChatConversationUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ChatConversationResponse> create(@Valid @RequestBody ChatConversationRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ChatConversationResponse.from(created));
    }

    @GetMapping
    public List<ChatConversationResponse> getAll() {
        return useCase.getAll().stream().map(ChatConversationResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ChatConversationResponse getById(@PathVariable UUID id) {
        return ChatConversationResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ChatConversationResponse update(@PathVariable UUID id, @Valid @RequestBody ChatConversationRequest request) {
        return ChatConversationResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
