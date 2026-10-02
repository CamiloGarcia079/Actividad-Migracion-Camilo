package com.mindconnect.infrastructure.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.study.ports.in.StudyUseCase;
import com.mindconnect.application.study.usecases.StudyService;
import com.mindconnect.domain.study.repositories.StudyRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class StudyBeansConfig {

    @Bean
    public StudyUseCase studyUseCase(StudyRepository studyRepository) {
        return new StudyService(studyRepository);
    }
}
