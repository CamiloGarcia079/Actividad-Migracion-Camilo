package com.mindconnect.infrastructure.contact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.contact.ports.in.ContactUseCase;
import com.mindconnect.application.contact.usecases.ContactService;
import com.mindconnect.domain.contact.repositories.ContactRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ContactBeansConfig {

    @Bean
    public ContactUseCase contactUseCase(ContactRepository contactRepository) {
        return new ContactService(contactRepository);
    }
}
