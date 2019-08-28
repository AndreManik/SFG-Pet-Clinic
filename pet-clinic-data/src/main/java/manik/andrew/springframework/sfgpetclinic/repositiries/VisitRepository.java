package manik.andrew.springframework.sfgpetclinic.repositiries;

import manik.andrew.springframework.sfgpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
