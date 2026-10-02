package com.mindconnect.infrastructure.assessmenttype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.assessmenttype.ports.in.AssessmentTypeUseCase;
import com.mindconnect.application.assessmenttype.usecases.AssessmentTypeService;
import com.mindconnect.domain.assessmenttype.repositories.AssessmentTypeRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class AssessmentTypeBeansConfig {

    @Bean
    public AssessmentTypeUseCase assessmentTypeUseCase(AssessmentTypeRepository assessmentTypeRepository) {
        return new AssessmentTypeService(assessmentTypeRepository);
    }
}
