package com.mindconnect.infrastructure.messagetype.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindconnect.application.messagetype.ports.in.MessageTypeUseCase;
import com.mindconnect.infrastructure.messagetype.adapters.in.rest.dtos.MessageTypeRequest;
import com.mindconnect.infrastructure.messagetype.adapters.in.rest.dtos.MessageTypeResponse;

/**
 * Adaptador de entrada REST para message_types.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/message-types")
public class MessageTypeController {

    private final MessageTypeUseCase useCase;

    public MessageTypeController(MessageTypeUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<MessageTypeResponse> create(@Valid @RequestBody MessageTypeRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(MessageTypeResponse.from(created));
    }

    @GetMapping
    public List<MessageTypeResponse> getAll() {
        return useCase.getAll().stream().map(MessageTypeResponse::from).toList();
    }

    @GetMapping("/{id}")
    public MessageTypeResponse getById(@PathVariable UUID id) {
        return MessageTypeResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public MessageTypeResponse update(@PathVariable UUID id, @Valid @RequestBody MessageTypeRequest request) {
        return MessageTypeResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
