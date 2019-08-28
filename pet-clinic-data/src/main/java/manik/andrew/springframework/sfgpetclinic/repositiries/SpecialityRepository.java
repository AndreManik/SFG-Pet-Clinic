package manik.andrew.springframework.sfgpetclinic.repositiries;

import manik.andrew.springframework.sfgpetclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
