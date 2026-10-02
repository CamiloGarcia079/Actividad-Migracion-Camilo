package com.mindconnect.domain.emailcontact.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.emailcontact.model.aggregates.EmailContact;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar email_contacts.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface EmailContactRepository {

    EmailContact save(EmailContact emailContact);

    Optional<EmailContact> findById(UUID id);

    List<EmailContact> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
