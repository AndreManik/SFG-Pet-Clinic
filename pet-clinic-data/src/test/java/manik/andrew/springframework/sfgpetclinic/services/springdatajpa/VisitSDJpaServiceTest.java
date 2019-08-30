package manik.andrew.springframework.sfgpetclinic.services.springdatajpa;

import manik.andrew.springframework.sfgpetclinic.model.Visit;
import manik.andrew.springframework.sfgpetclinic.repositiries.VisitRepository;
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
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    final Long ID = 1L;
    Visit visit;

    @BeforeEach
    void setUp() {
        visit = Visit.builder().id(ID).build();
    }

    @Test
    void findAll() {
        Set<Visit> returnVisitSet = new HashSet<>();
        returnVisitSet.add(visit);
        returnVisitSet.add(Visit.builder().id(2L).build());

        when(visitRepository.findAll()).thenReturn(returnVisitSet);

        Set<Visit> visits = service.findAll();

        assertNotNull(visits);
        assertEquals(2, visits.size());
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));
        Visit foundVisit = service.findById(ID);
        assertNotNull(foundVisit);
        assertEquals(visit, foundVisit);
    }

    @Test
    void findByIdNoFound() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.empty());
        Visit foundVisit = service.findById(ID);
        assertNull(foundVisit);
    }

    @Test
    void save() {
        when(visitRepository.save(any())).thenReturn(visit);

        Visit savedVisit = service.save(visit);
        assertNotNull(savedVisit);
        assertEquals(visit, savedVisit);
    }

    @Test
    void delete() {
        service.delete(visit);
        verify(visitRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);
        verify(visitRepository).deleteById(anyLong());
    }
}