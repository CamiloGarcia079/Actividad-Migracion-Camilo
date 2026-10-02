package com.mindconnect.domain.contact.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.contact.model.aggregates.Contact;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar contacts.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ContactRepository {

    Contact save(Contact contact);

    Optional<Contact> findById(UUID id);

    List<Contact> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
