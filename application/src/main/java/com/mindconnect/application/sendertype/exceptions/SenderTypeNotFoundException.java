package com.mindconnect.application.sendertype.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de sender_types con el id pedido.
 */
public class SenderTypeNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public SenderTypeNotFoundException(UUID id) {
        super("No se encontró un registro de sender_types con id " + id);
    }
}
