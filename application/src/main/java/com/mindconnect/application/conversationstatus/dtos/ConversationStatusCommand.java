package com.mindconnect.application.conversationstatus.dtos;

/**
 * Datos que entran al caso de uso de conversations_statuses.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ConversationStatusCommand(
        String nameStatus) {
}
