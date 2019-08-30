package manik.andrew.springframework.sfgpetclinic.services.springdatajpa;

import manik.andrew.springframework.sfgpetclinic.model.PetType;
import manik.andrew.springframework.sfgpetclinic.repositiries.PetTypeRepository;
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
class PetTypeSDJpaServiceTest {

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeSDJpaService service;

    final Long ID = 1L;

    PetType petType;

    @BeforeEach
    void setUp() {
        petType = PetType.builder().id(ID).build();
    }

    @Test
    void findAll() {
        Set<PetType> returnPetTypeSet = new HashSet<>();
        returnPetTypeSet.add(petType);
        returnPetTypeSet.add(PetType.builder().id(2L).build());

        when(petTypeRepository.findAll()).thenReturn(returnPetTypeSet);

        Set<PetType> petTypes = service.findAll();

        assertNotNull(petTypes);
        assertEquals(2, petTypes.size());

        service.findAll();
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(petType));
        PetType foundPetType = service.findById(ID);
        assertNotNull(foundPetType);
        assertEquals(ID, foundPetType.getId());
    }

    @Test
    void findByIdNoFound() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.empty());
        PetType foundPetType = service.findById(ID);
        assertNull(foundPetType);
    }

    @Test
    void save() {
        when(petTypeRepository.save(any())).thenReturn(petType);

        PetType savedPetType = service.save(petType);
        assertNotNull(savedPetType);
        assertEquals(petType, savedPetType);
    }

    @Test
    void delete() {
        service.delete(petType);
        verify(petTypeRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);
        verify(petTypeRepository).deleteById(any());
    }
}