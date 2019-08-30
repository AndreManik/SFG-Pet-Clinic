package manik.andrew.springframework.sfgpetclinic.services.springdatajpa;

import manik.andrew.springframework.sfgpetclinic.model.Vet;
import manik.andrew.springframework.sfgpetclinic.repositiries.VetRepository;
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
class VetSDJpaServiceTest {

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetSDJpaService service;

    final Long ID = 1L;
    Vet vet;

    @BeforeEach
    void setUp() {
        vet = Vet.builder().id(ID).specialities(new HashSet<>()).build();
    }

    @Test
    void findAll() {
        Set<Vet> returnVetSet = new HashSet<>();
        returnVetSet.add(vet);
        returnVetSet.add(Vet.builder().id(2L).specialities(new HashSet<>()).build());

        when(vetRepository.findAll()).thenReturn(returnVetSet);

        Set<Vet> vets = service.findAll();

        assertNotNull(vets);
        assertEquals(2, vets.size());
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(vet));
        Vet foundVet = service.findById(ID);
        assertNotNull(foundVet);
        assertEquals(ID, foundVet.getId());
    }

    @Test
    void findByIdNoFound() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.empty());
        Vet foundVet = service.findById(ID);
        assertNull(foundVet);
    }

    @Test
    void save() {
        when(vetRepository.save(any())).thenReturn(vet);

        Vet savedVet = service.save(vet);

        assertNotNull(savedVet);
        assertEquals(vet, savedVet);

    }

    @Test
    void delete() {
        service.delete(vet);
        verify(vetRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);
        verify(vetRepository).deleteById(anyLong());
    }
}