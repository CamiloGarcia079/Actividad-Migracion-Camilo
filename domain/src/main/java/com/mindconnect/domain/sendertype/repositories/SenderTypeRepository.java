package com.mindconnect.domain.sendertype.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.sendertype.model.aggregates.SenderType;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar sender_types.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface SenderTypeRepository {

    SenderType save(SenderType senderType);

    Optional<SenderType> findById(UUID id);

    List<SenderType> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
