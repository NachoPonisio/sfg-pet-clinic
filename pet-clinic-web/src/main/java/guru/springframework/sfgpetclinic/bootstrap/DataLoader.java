package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
//when registered as component, it runs the Run method implemented from CommandLineRunner
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }


    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Jackson");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Bruno");
        owner2.setLastName("Diaz");

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