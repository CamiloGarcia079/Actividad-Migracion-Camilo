package com.mindconnect.infrastructure.country.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.country.ports.in.CountryUseCase;
import com.mindconnect.application.country.usecases.CountryService;
import com.mindconnect.domain.country.repositories.CountryRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class CountryBeansConfig {

    @Bean
    public CountryUseCase countryUseCase(CountryRepository countryRepository) {
        return new CountryService(countryRepository);
    }
}
