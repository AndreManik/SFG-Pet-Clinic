package manik.andrew.springframework.sfgpetclinic.services.map;

import manik.andrew.springframework.sfgpetclinic.model.Owner;
import manik.andrew.springframework.sfgpetclinic.model.Pet;
import manik.andrew.springframework.sfgpetclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VisitMapServiceTest {

    VisitMapService visitMapService;
    final Long id = 1L;
    final Visit visit = Visit.builder().id(id).pet(Pet.builder().id(id).owner(Owner.builder().id(id).build()).build()).build();


    @BeforeEach
    void setUp() {
        visitMapService = new VisitMapService();
        visitMapService.save(visit);
    }

    @Test
    void findAll() {
        assertEquals(1, visitMapService.findAll().size());
    }

    @Test
    void deleteById() {
        visitMapService.deleteById(id);
        assertEquals(0, visitMapService.findAll().size());
    }

    @Test
    void delete() {
        visitMapService.delete(visit);
        assertEquals(0, visitMapService.findAll().size());
    }

    @Test
    void save() {
        Long id = 2L;
        Visit visitNew = Visit.builder().id(id).pet(Pet.builder().id(id).owner(Owner.builder().id(id).build()).build()).build();
        Visit savedVisit = visitMapService.save(visitNew);
        assertNotNull(savedVisit);
        assertEquals(visitNew, savedVisit);
    }

    @Test
    void saveNoId() {
        Visit visitNew = Visit.builder().id(id).pet(Pet.builder().id(id).owner(Owner.builder().id(id).build()).build()).build();
        Visit savedVisit = visitMapService.save(visitNew);
        assertNotNull(savedVisit);
        assertNotNull(savedVisit.getPet());
    }

    @Test
    void findById() {
        Visit savedVisit = visitMapService.findById(id);
        assertNotNull(savedVisit);
        assertEquals(visit, savedVisit);
    }
}