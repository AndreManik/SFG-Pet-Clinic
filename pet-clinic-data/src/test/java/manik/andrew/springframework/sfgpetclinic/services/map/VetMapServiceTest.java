package manik.andrew.springframework.sfgpetclinic.services.map;

import manik.andrew.springframework.sfgpetclinic.model.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class VetMapServiceTest {

    VetMapService vetMapService;

    final Long id = 1L;
    final Vet vet = Vet.builder().id(id).specialities(new HashSet<>()).build();

    @BeforeEach
    void setUp() {
        vetMapService = new VetMapService(new SpecialityMapService());
        vetMapService.save(vet);
    }

    @Test
    void findAll() {
        org.junit.jupiter.api.Assertions.assertEquals(1, vetMapService.findAll().size());
    }

    @Test
    void deleteById() {
        vetMapService.deleteById(id);
        org.junit.jupiter.api.Assertions.assertEquals(0, vetMapService.findAll().size());
    }

    @Test
    void delete() {
        vetMapService.delete(vet);
        org.junit.jupiter.api.Assertions.assertEquals(0, vetMapService.findAll().size());
    }

    @Test
    void save() {
        Long id = 2L;
        Vet vetNew = Vet.builder().id(id).specialities(new HashSet<>()).build();
        Vet savedVet = vetMapService.save(vetNew);
        org.junit.jupiter.api.Assertions.assertNotNull(savedVet);
        org.junit.jupiter.api.Assertions.assertEquals(vetNew, savedVet);
    }

    @Test
    void saveNoId() {
        Vet vetNew = Vet.builder().id(id).specialities(new HashSet<>()).build();
        Vet savedVet = vetMapService.save(vetNew);
        org.junit.jupiter.api.Assertions.assertNotNull(savedVet);
        org.junit.jupiter.api.Assertions.assertNotNull(savedVet.getId());
    }
}
