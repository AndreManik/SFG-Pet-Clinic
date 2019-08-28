package manik.andrew.springframework.sfgpetclinic.repositiries;

import manik.andrew.springframework.sfgpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
