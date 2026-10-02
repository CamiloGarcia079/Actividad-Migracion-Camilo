package com.mindconnect.application.messagetype.dtos;

/**
 * Datos que entran al caso de uso de message_types.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record MessageTypeCommand(
        String nameType) {
}
