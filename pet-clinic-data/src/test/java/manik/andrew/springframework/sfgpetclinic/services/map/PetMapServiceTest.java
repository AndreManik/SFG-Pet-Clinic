package manik.andrew.springframework.sfgpetclinic.services.map;

import manik.andrew.springframework.sfgpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetMapServiceTest {

    PetMapService petMapService;
    final String petName = "Pet";
    final Long id = 1L;
    final Pet pet = Pet.builder().id(id).name(petName).build();


    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(pet);
    }

    @Test
    void findAll() {
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void findById() {
        Pet foundPet = petMapService.findById(id);
        assertNotNull(foundPet);
        assertEquals(pet, foundPet);
    }

    @Test
    void deleteById() {
        petMapService.deleteById(id);
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void delete() {
        petMapService.delete(pet);
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void save() {
        Long id = 2L;
        Pet petNew = Pet.builder().id(id).build();
        Pet savedPet = petMapService.save(petNew);
        assertNotNull(savedPet);
        assertEquals(petNew, savedPet);
    }

    @Test
    void saveNotId() {
        Pet petNew = Pet.builder().build();
        Pet savedPet = petMapService.save(petNew);
        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
    }
}