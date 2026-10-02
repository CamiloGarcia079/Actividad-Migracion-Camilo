package com.mindconnect.application.contact.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de contacts con el id pedido.
 */
public class ContactNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ContactNotFoundException(UUID id) {
        super("No se encontró un registro de contacts con id " + id);
    }
}
