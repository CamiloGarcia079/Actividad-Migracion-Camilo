package com.mindconnect.application.chatairunmetric.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de chat_ai_run_metrics con el id pedido.
 */
public class ChatAiRunMetricNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatAiRunMetricNotFoundException(UUID id) {
        super("No se encontró un registro de chat_ai_run_metrics con id " + id);
    }
}
