package manik.andrew.springframework.sfgpetclinic.services.springdatajpa;

import manik.andrew.springframework.sfgpetclinic.model.Speciality;
import manik.andrew.springframework.sfgpetclinic.repositiries.SpecialityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialityRepository specialityRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    final Long ID = 1L;

    Speciality speciality;

    @BeforeEach
    void setUp() {
        speciality = Speciality.builder().id(ID).build();
    }

    @Test
    void findAll() {
        Set<Speciality> returnedSpecialitySet = new HashSet<>();
        returnedSpecialitySet.add(speciality);
        returnedSpecialitySet.add(Speciality.builder().id(2L).build());

        when(specialityRepository.findAll()).thenReturn(returnedSpecialitySet);

        Set<Speciality> specialities = service.findAll();

        assertNotNull(specialities);
        assertEquals(2, specialities.size());
    }

    @Test
    void findById() {
        when(specialityRepository.findById(anyLong())).thenReturn(Optional.of(speciality));
        Speciality foundSpeciality = service.findById(ID);
        assertNotNull(foundSpeciality);
        assertEquals(ID, foundSpeciality.getId());
    }

    @Test
    void findByIdNoFound() {
        when(specialityRepository.findById(anyLong())).thenReturn(Optional.empty());
        Speciality foundSpeciality = service.findById(ID);
        assertNull(foundSpeciality);
    }


    @Test
    void save() {
        when(specialityRepository.save(any())).thenReturn(speciality);
        Speciality foundSpeciality = service.save(speciality);
        assertNotNull(foundSpeciality);
        assertEquals(speciality, foundSpeciality);
    }

    @Test
    void delete() {
        service.delete(speciality);
        verify(specialityRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);
        verify(specialityRepository).deleteById(anyLong());
    }
}