package com.mindconnect.infrastructure.chatescalationassignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatescalationassignment.ports.in.ChatEscalationAssignmentUseCase;
import com.mindconnect.application.chatescalationassignment.usecases.ChatEscalationAssignmentService;
import com.mindconnect.domain.chatescalationassignment.repositories.ChatEscalationAssignmentRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ChatEscalationAssignmentBeansConfig {

    @Bean
    public ChatEscalationAssignmentUseCase chatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository chatEscalationAssignmentRepository) {
        return new ChatEscalationAssignmentService(chatEscalationAssignmentRepository);
    }
}
