package com.mindconnect.application.chatescalationstatushistory.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de chat_escalation_status_history con el id pedido.
 */
public class ChatEscalationStatusHistoryNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatEscalationStatusHistoryNotFoundException(UUID id) {
        super("No se encontró un registro de chat_escalation_status_history con id " + id);
    }
}
