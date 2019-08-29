package manik.andrew.springframework.sfgpetclinic.services.map;

import manik.andrew.springframework.sfgpetclinic.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SpecialityMapServiceTest {

    SpecialityMapService specialityMapService;
    final Long id = 1L;
    final String description = "description";
    final Speciality speciality = Speciality.builder().id(id).description(description).build();


    @BeforeEach
    void setUp() {
        specialityMapService = new SpecialityMapService();
        specialityMapService.save(speciality);
    }

    @Test
    void findAll() {
        assertEquals(1, specialityMapService.findAll().size());
    }

    @Test
    void deleteById() {
        specialityMapService.deleteById(id);
        assertEquals(0, specialityMapService.findAll().size());
    }

    @Test
    void delete() {
        specialityMapService.delete(speciality);
        assertEquals(0, specialityMapService.findAll().size());
    }

    @Test
    void save() {
        Long id = 2L;
        Speciality specialityNew = Speciality.builder().id(id).build();
        Speciality savedSpeciality = specialityMapService.save(specialityNew);
        assertNotNull(savedSpeciality);
        assertEquals(specialityNew, savedSpeciality);
    }

    @Test
    void saveNoId() {
        Speciality specialityNew = Speciality.builder().build();
        Speciality savedSpeciality = specialityMapService.save(specialityNew);
        assertNotNull(savedSpeciality);
        assertEquals(specialityNew, savedSpeciality);
    }

    @Test
    void findById() {
        Speciality foundSpeciality = specialityMapService.findById(id);
        assertNotNull(foundSpeciality);
        assertEquals(speciality, foundSpeciality);
    }
}