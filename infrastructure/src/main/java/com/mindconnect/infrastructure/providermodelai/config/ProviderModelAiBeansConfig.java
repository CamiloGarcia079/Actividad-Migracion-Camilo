package com.mindconnect.infrastructure.providermodelai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.providermodelai.ports.in.ProviderModelAiUseCase;
import com.mindconnect.application.providermodelai.usecases.ProviderModelAiService;
import com.mindconnect.domain.providermodelai.repositories.ProviderModelAiRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ProviderModelAiBeansConfig {

    @Bean
    public ProviderModelAiUseCase providerModelAiUseCase(ProviderModelAiRepository providerModelAiRepository) {
        return new ProviderModelAiService(providerModelAiRepository);
    }
}
