package com.mindconnect.application.phonecontact.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.phonecontact.dtos.PhoneContactCommand;
import com.mindconnect.domain.phonecontact.model.aggregates.PhoneContact;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre phone_contacts.
 */
public interface PhoneContactUseCase {

    PhoneContact create(PhoneContactCommand command);

    PhoneContact getById(UUID id);

    List<PhoneContact> getAll();

    PhoneContact update(UUID id, PhoneContactCommand command);

    void delete(UUID id);
}
