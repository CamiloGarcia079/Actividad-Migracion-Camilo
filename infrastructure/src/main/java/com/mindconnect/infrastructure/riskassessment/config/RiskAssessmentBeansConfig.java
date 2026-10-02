package com.mindconnect.infrastructure.riskassessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.riskassessment.ports.in.RiskAssessmentUseCase;
import com.mindconnect.application.riskassessment.usecases.RiskAssessmentService;
import com.mindconnect.domain.riskassessment.repositories.RiskAssessmentRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class RiskAssessmentBeansConfig {

    @Bean
    public RiskAssessmentUseCase riskAssessmentUseCase(RiskAssessmentRepository riskAssessmentRepository) {
        return new RiskAssessmentService(riskAssessmentRepository);
    }
}
