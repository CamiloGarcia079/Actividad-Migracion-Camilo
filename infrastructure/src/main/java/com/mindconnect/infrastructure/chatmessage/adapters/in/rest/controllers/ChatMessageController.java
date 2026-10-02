package com.mindconnect.infrastructure.chatmessage.adapters.in.rest.controllers;

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

import com.mindconnect.application.chatmessage.ports.in.ChatMessageUseCase;
import com.mindconnect.infrastructure.chatmessage.adapters.in.rest.dtos.ChatMessageRequest;
import com.mindconnect.infrastructure.chatmessage.adapters.in.rest.dtos.ChatMessageResponse;

/**
 * Adaptador de entrada REST para chat_messages.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/chat-messages")
public class ChatMessageController {

    private final ChatMessageUseCase useCase;

    public ChatMessageController(ChatMessageUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ChatMessageResponse> create(@Valid @RequestBody ChatMessageRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ChatMessageResponse.from(created));
    }

    @GetMapping
    public List<ChatMessageResponse> getAll() {
        return useCase.getAll().stream().map(ChatMessageResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ChatMessageResponse getById(@PathVariable UUID id) {
        return ChatMessageResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ChatMessageResponse update(@PathVariable UUID id, @Valid @RequestBody ChatMessageRequest request) {
        return ChatMessageResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
