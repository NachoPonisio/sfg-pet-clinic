package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
//when registered as component, it runs the Run method implemented from CommandLineRunner
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }


    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);


        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Jackson");
        owner1.setAddress("Federico Lacroze 3444");
        owner1.setCity("Buenos Aires");
        owner1.setTelephone("1536789982");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");

        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Bruno");
        owner2.setLastName("Diaz");
        owner2.setAddress("Av Diaz 100");
        owner2.setCity("Ciudad Gotica");
        owner2.setTelephone("1567772892");

        Pet brunosPet = new Pet();
        brunosPet.setPetType(savedCatPetType);
        brunosPet.setOwner(owner2);
        brunosPet.setBirthDate(LocalDate.now());
        brunosPet.setName("Carloncho");

        owner2.getPets().add(brunosPet);

        ownerService.save(owner2);

        System.out.println("Owners loaded...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Carlos");
        vet1.setLastName("Culebra");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Aquiles");
        vet2.setLastName("Bailoyo");

        vetService.save(vet2);

        System.out.println("Vets loaded...");



    }
}
