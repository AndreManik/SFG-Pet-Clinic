package manik.andrew.springframework.sfgpetclinic.services.springdatajpa;

import manik.andrew.springframework.sfgpetclinic.model.Owner;
import manik.andrew.springframework.sfgpetclinic.repositiries.OwnerRepository;
import manik.andrew.springframework.sfgpetclinic.repositiries.PetRepository;
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
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Smith";
    public static final long ID = 1L;
    Owner retOwner;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @Mock
    PetRepository petRepository;

    @InjectMocks
    OwnerSDJpaService service;

    @BeforeEach
    void setUp() {
        retOwner = Owner.builder().id(ID).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(retOwner);
        Owner savedOwner = service.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, savedOwner.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnersSet = new HashSet<>();
        returnOwnersSet.add(retOwner);
        returnOwnersSet.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(retOwner));
        Owner foundOwner = service.findById(ID);
        assertNotNull(foundOwner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner foundOwner = service.findById(ID);
        assertNull(foundOwner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(2L).lastName("Name").build();
        when(ownerRepository.save(any())).thenReturn(ownerToSave);
        Owner savedOwner = service.save(ownerToSave);
        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        service.delete(retOwner);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);
        verify(ownerRepository).deleteById(any());
    }
}