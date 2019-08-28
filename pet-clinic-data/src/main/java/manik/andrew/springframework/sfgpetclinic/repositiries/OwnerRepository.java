package manik.andrew.springframework.sfgpetclinic.repositiries;

import manik.andrew.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
