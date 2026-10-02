package com.mindconnect.infrastructure.diagnosticsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.diagnosticsystem.ports.in.DiagnosticSystemUseCase;
import com.mindconnect.application.diagnosticsystem.usecases.DiagnosticSystemService;
import com.mindconnect.domain.diagnosticsystem.repositories.DiagnosticSystemRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class DiagnosticSystemBeansConfig {

    @Bean
    public DiagnosticSystemUseCase diagnosticSystemUseCase(DiagnosticSystemRepository diagnosticSystemRepository) {
        return new DiagnosticSystemService(diagnosticSystemRepository);
    }
}
