package manik.andrew.springframework.sfgpetclinic.services.map;

import manik.andrew.springframework.sfgpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PetTypeMapServiceTest {

    PetTypeMapService petTypeMapService;
    final String name = "Pet Type Name";
    final Long id = 1L;
    final PetType petType = PetType.builder().id(id).name(name).build();

    @BeforeEach
    void setUp() {
        petTypeMapService = new PetTypeMapService();
        petTypeMapService.save(petType);
    }

    @Test
    void findAll() {
        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void findById() {
        PetType foundPet = petTypeMapService.findById(id);
        assertNotNull(foundPet);
        assertEquals(petType, foundPet);
    }

    @Test
    void delete() {
        petTypeMapService.delete(petType);
        assertEquals(0, petTypeMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petTypeMapService.deleteById(id);
        assertEquals(0, petTypeMapService.findAll().size());
    }

    @Test
    void save() {
        Long id = 2L;
        PetType petTypeNew = PetType.builder().id(id).build();
        PetType savedPetType = petTypeMapService.save(petTypeNew);
        assertNotNull(savedPetType);
        assertEquals(petTypeNew, savedPetType);
    }

    @Test
    void saveNotId() {
        PetType petTypeNew = PetType.builder().build();
        PetType savedPetType = petTypeMapService.save(petTypeNew);
        assertNotNull(savedPetType);
        assertNotNull(savedPetType.getId());
    }
}
