package com.mindconnect.infrastructure.chatmessage.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.mindconnect.application.chatmessage.dtos.ChatMessageCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de chat_messages.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ChatMessageRequest(
        @NotNull UUID conversationId,
        @NotNull UUID messageTypeId,
        @NotNull UUID participantId,
        @NotBlank String content,
        @NotBlank String metadata) {

    public ChatMessageCommand toCommand() {
        return new ChatMessageCommand(
                conversationId, messageTypeId, participantId, content, metadata);
    }
}
