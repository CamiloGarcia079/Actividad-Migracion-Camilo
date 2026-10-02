package com.mindconnect.application.phonecontact.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de phone_contacts con el id pedido.
 */
public class PhoneContactNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public PhoneContactNotFoundException(UUID id) {
        super("No se encontró un registro de phone_contacts con id " + id);
    }
}
