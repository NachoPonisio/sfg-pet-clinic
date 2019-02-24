package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    private String LAST_NAME = "Smith";
    private Long ID_VALUE = 1L;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetTypeRepository petTypeRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerSDJpaService ownerSDJpaService;


    Owner returnOwner;

    @BeforeEach
    private void setUp(){

        returnOwner = Owner.builder().id(ID_VALUE).lastName(LAST_NAME).build();

    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any(String.class))).thenReturn(returnOwner);

        Owner smith = ownerSDJpaService.findByLastName(LAST_NAME);

        assertNotNull(smith);
        assertEquals(smith.getLastName(), LAST_NAME);
        verify(ownerRepository).findByLastName(any(String.class));
    }

    @Test
    void findAll() {

        Set<Owner> ownerSet = new HashSet<Owner>();
        Owner owner1 = Owner.builder().id(2L).build();
        Owner owner2 = Owner.builder().id(3L).build();

        ownerSet.add(owner1);
        ownerSet.add(owner2);

        when(ownerRepository.findAll()).thenReturn(ownerSet);

        Set<Owner> returnedOwners = ownerSDJpaService.findAll();

        assertNotNull(returnedOwners);
        assertTrue(returnedOwners.size() == 2);
        verify(ownerRepository).findAll();

    }

    @Test
    void findById() {

        when(ownerRepository.findById(any(Long.class))).thenReturn(Optional.of(returnOwner));

        Owner foundOwner = ownerSDJpaService.findById(ID_VALUE);

        assertNotNull(foundOwner);
        assertEquals(foundOwner.getId(), ID_VALUE);
        assertEquals(foundOwner.getLastName(), LAST_NAME);
        verify(ownerRepository).findById(anyLong());

    }

    @Test
    void save() {

        Owner anOwner = Owner.builder().id(2L).build();

        when(ownerRepository.save(any(Owner.class))).thenReturn(returnOwner);

        Owner savedOwner = ownerSDJpaService.save(anOwner);

        assertNotNull(savedOwner);
        assertEquals(ID_VALUE, savedOwner.getId());
        assertEquals(LAST_NAME, savedOwner.getLastName());
        verify(ownerRepository).save(any(Owner.class));

    }

    @Test
    void delete() {

        Owner ownerToDelete = returnOwner;

        ownerSDJpaService.delete(ownerToDelete);

        verify(ownerRepository, times(1)).delete(any(Owner.class));


    }

    @Test
    void deleteById() {
        Long idToDelete = ID_VALUE;

        ownerSDJpaService.deleteById(idToDelete);

        verify(ownerRepository, times(1)).deleteById(anyLong());

    }
}