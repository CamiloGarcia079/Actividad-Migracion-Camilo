package com.mindconnect.domain.shared.validation;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DomainValidationsTest {

    @Test
    void aceptaUnValorPresente() {
        assertDoesNotThrow(() -> DomainValidations.required("algo", "campo"));
    }

    @Test
    void rechazaUnValorNulo() {
        assertThrows(DomainException.class, () -> DomainValidations.required(null, "campo"));
    }
}
