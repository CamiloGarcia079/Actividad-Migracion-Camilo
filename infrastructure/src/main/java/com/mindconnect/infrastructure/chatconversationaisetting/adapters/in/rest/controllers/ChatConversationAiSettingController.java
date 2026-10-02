package com.mindconnect.infrastructure.chatconversationaisetting.adapters.in.rest.controllers;

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

import com.mindconnect.application.chatconversationaisetting.ports.in.ChatConversationAiSettingUseCase;
import com.mindconnect.infrastructure.chatconversationaisetting.adapters.in.rest.dtos.ChatConversationAiSettingRequest;
import com.mindconnect.infrastructure.chatconversationaisetting.adapters.in.rest.dtos.ChatConversationAiSettingResponse;

/**
 * Adaptador de entrada REST para chat_conversation_ai_settings.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/chat-conversation-ai-settings")
public class ChatConversationAiSettingController {

    private final ChatConversationAiSettingUseCase useCase;

    public ChatConversationAiSettingController(ChatConversationAiSettingUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ChatConversationAiSettingResponse> create(@Valid @RequestBody ChatConversationAiSettingRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ChatConversationAiSettingResponse.from(created));
    }

    @GetMapping
    public List<ChatConversationAiSettingResponse> getAll() {
        return useCase.getAll().stream().map(ChatConversationAiSettingResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ChatConversationAiSettingResponse getById(@PathVariable UUID id) {
        return ChatConversationAiSettingResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ChatConversationAiSettingResponse update(@PathVariable UUID id, @Valid @RequestBody ChatConversationAiSettingRequest request) {
        return ChatConversationAiSettingResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
