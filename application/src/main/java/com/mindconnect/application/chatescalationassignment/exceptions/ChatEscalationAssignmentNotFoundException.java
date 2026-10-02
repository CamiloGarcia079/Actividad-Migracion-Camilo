package com.mindconnect.application.chatescalationassignment.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de chat_escalation_assignments con el id pedido.
 */
public class ChatEscalationAssignmentNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatEscalationAssignmentNotFoundException(UUID id) {
        super("No se encontró un registro de chat_escalation_assignments con id " + id);
    }
}
