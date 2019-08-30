package manik.andrew.springframework.sfgpetclinic.services.springdatajpa;

import manik.andrew.springframework.sfgpetclinic.model.Pet;
import manik.andrew.springframework.sfgpetclinic.repositiries.PetRepository;
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
class PetSDJpaServiceTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDJpaService service;

    final Long ID = 1L;
    Pet pet;

    @BeforeEach
    void setUp() {
        pet = Pet.builder().id(ID).build();
    }

    @Test
    void findAll() {
        Set<Pet> returnedPetSet = new HashSet<>();
        returnedPetSet.add(pet);
        returnedPetSet.add(Pet.builder().id(2L).build());

        when(petRepository.findAll()).thenReturn(returnedPetSet);

        Set<Pet> pets = service.findAll();
        assertNotNull(pets);
        assertEquals(2, pets.size());

    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(pet));
        Pet returnedPet = service.findById(ID);
        assertNotNull(returnedPet);
        assertEquals(ID, returnedPet.getId());
    }

    @Test
    void findByIdNoFound() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());
        Pet returnedPet = service.findById(ID);
        assertNull(returnedPet);
    }


    @Test
    void save() {
        when(petRepository.save(any())).thenReturn(pet);
        Pet returnedPet = service.save(pet);
        assertNotNull(returnedPet);
    }

    @Test
    void delete() {
        service.delete(pet);
        verify(petRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);
        verify(petRepository).deleteById(any());
    }
}