package com.mindconnect.application.emailcontact.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de email_contacts con el id pedido.
 */
public class EmailContactNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public EmailContactNotFoundException(UUID id) {
        super("No se encontró un registro de email_contacts con id " + id);
    }
}
