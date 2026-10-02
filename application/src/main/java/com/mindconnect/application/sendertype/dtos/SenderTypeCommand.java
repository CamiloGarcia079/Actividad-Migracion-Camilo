package com.mindconnect.application.sendertype.dtos;

/**
 * Datos que entran al caso de uso de sender_types.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record SenderTypeCommand(
        String nameType) {
}
