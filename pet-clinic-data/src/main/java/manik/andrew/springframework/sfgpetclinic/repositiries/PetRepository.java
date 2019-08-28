package manik.andrew.springframework.sfgpetclinic.repositiries;

import manik.andrew.springframework.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
