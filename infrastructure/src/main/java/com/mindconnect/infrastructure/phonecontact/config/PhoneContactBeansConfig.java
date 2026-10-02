package com.mindconnect.infrastructure.phonecontact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.phonecontact.ports.in.PhoneContactUseCase;
import com.mindconnect.application.phonecontact.usecases.PhoneContactService;
import com.mindconnect.domain.phonecontact.repositories.PhoneContactRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class PhoneContactBeansConfig {

    @Bean
    public PhoneContactUseCase phoneContactUseCase(PhoneContactRepository phoneContactRepository) {
        return new PhoneContactService(phoneContactRepository);
    }
}
