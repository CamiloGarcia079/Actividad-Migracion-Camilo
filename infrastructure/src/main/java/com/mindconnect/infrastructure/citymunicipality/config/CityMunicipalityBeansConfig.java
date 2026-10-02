package com.mindconnect.infrastructure.citymunicipality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.citymunicipality.ports.in.CityMunicipalityUseCase;
import com.mindconnect.application.citymunicipality.usecases.CityMunicipalityService;
import com.mindconnect.domain.citymunicipality.repositories.CityMunicipalityRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class CityMunicipalityBeansConfig {

    @Bean
    public CityMunicipalityUseCase cityMunicipalityUseCase(CityMunicipalityRepository cityMunicipalityRepository) {
        return new CityMunicipalityService(cityMunicipalityRepository);
    }
}
