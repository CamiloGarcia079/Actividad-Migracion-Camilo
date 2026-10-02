package com.mindconnect.domain.phonecontact.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.phonecontact.model.aggregates.PhoneContact;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar phone_contacts.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface PhoneContactRepository {

    PhoneContact save(PhoneContact phoneContact);

    Optional<PhoneContact> findById(UUID id);

    List<PhoneContact> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
