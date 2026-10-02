package com.mindconnect.application.mentalstatusexam.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de mental_status_exams con el id pedido.
 */
public class MentalStatusExamNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public MentalStatusExamNotFoundException(UUID id) {
        super("No se encontró un registro de mental_status_exams con id " + id);
    }
}
