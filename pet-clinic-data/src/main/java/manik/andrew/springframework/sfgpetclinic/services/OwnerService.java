package manik.andrew.springframework.sfgpetclinic.services;

import manik.andrew.springframework.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
