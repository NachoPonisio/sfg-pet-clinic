package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    private final Long ID = new Long(1L);
    private final String LAST_NAME = "Gonzalez";
    Owner firstOwner;

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        firstOwner =  ownerServiceMap.save(Owner.builder().id(ID).lastName(LAST_NAME).build());

    }

    @Test
    private void findAll(){
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        log.debug("OwnerSet.size() = " + ownerSet.size());
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ID);
        log.debug("Owner.getId() = " + owner.getId());
        assertEquals(ID, owner.getId());
    }

    @Test
    void save() {
        Owner newOwner = Owner.builder().id(2L).lastName("Perez").build();
        ownerServiceMap.save(newOwner);
        assertEquals(Long.valueOf(2L), ownerServiceMap.findById(2L).getId());
        assertEquals("Perez", ownerServiceMap.findById(2L).getLastName());
    }

    @Test
    void saveNoId(){
        Owner noIdOwner = Owner.builder().build();
        Owner savedOwner = ownerServiceMap.save(noIdOwner);

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
        log.debug("savedOwner.getId() = " + savedOwner.getId());

    }

    @Test
    void delete() {
        ownerServiceMap.delete(firstOwner);
        Set<Owner> ownerSet = ownerServiceMap.findAll();

        assertEquals(0, ownerSet.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ID);
        Set<Owner> ownerSet = ownerServiceMap.findAll();

        assertEquals(0, ownerSet.size());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, owner.getLastName());
    }
}