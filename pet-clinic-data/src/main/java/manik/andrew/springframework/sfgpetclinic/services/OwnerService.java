package manik.andrew.springframework.sfgpetclinic.services;

import manik.andrew.springframework.sfgpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findByLastName(String lasnName);
    Owner findById(Long id);
    Owner save(Owner owner);
    Set<Owner> findAll();
}
