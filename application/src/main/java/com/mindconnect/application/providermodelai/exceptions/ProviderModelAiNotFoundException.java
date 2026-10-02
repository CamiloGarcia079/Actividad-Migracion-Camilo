package com.mindconnect.application.providermodelai.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de provider_models_ai con el id pedido.
 */
public class ProviderModelAiNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ProviderModelAiNotFoundException(UUID id) {
        super("No se encontró un registro de provider_models_ai con id " + id);
    }
}
