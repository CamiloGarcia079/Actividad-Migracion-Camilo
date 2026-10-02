package com.mindconnect.infrastructure.emailcontact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.emailcontact.ports.in.EmailContactUseCase;
import com.mindconnect.application.emailcontact.usecases.EmailContactService;
import com.mindconnect.domain.emailcontact.repositories.EmailContactRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class EmailContactBeansConfig {

    @Bean
    public EmailContactUseCase emailContactUseCase(EmailContactRepository emailContactRepository) {
        return new EmailContactService(emailContactRepository);
    }
}
