package com.mindconnect.infrastructure.clinicalnote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.clinicalnote.ports.in.ClinicalNoteUseCase;
import com.mindconnect.application.clinicalnote.usecases.ClinicalNoteService;
import com.mindconnect.domain.clinicalnote.repositories.ClinicalNoteRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ClinicalNoteBeansConfig {

    @Bean
    public ClinicalNoteUseCase clinicalNoteUseCase(ClinicalNoteRepository clinicalNoteRepository) {
        return new ClinicalNoteService(clinicalNoteRepository);
    }
}
