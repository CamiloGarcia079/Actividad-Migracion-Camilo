package com.mindconnect.infrastructure.risklevel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.risklevel.ports.in.RiskLevelUseCase;
import com.mindconnect.application.risklevel.usecases.RiskLevelService;
import com.mindconnect.domain.risklevel.repositories.RiskLevelRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class RiskLevelBeansConfig {

    @Bean
    public RiskLevelUseCase riskLevelUseCase(RiskLevelRepository riskLevelRepository) {
        return new RiskLevelService(riskLevelRepository);
    }
}
