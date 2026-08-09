package de.iu.ghostnetfishing;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final GhostNetRepository ghostNetRepository;
    private final PersonRepository personRepository;

    public DataInitializer(GhostNetRepository ghostNetRepository, PersonRepository personRepository) {
        this.ghostNetRepository = ghostNetRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) {
        // Nur Beispieldaten anlegen, wenn die Datenbank noch leer ist
        if (ghostNetRepository.count() == 0) {

            // 1. Beispiel: Offenes Netz (User Story 1 & 3)
            GhostNet net1 = new GhostNet();
            net1.setStandort("54.123, 10.456 (Ostsee nahe Kiel)");
            net1.setGroesse("ca. 10m x 5m");
            net1.setStatus(Status.GEMELDET);
            ghostNetRepository.save(net1);

            // 2. Beispiel: Netz mit geplanter Bergung (User Story 2)
            Person bergender = new Person("Anna Schmidt", "0171-1234567");
            personRepository.save(bergender);

            GhostNet net2 = new GhostNet();
            net2.setStandort("53.800, 8.600 (Nordsee nahe Cuxhaven)");
            net2.setGroesse("ca. 25m Stellnetz");
            net2.setStatus(Status.BERGUNG_BEVORSTEHEND);
            net2.setBergendePerson(bergender);
            ghostNetRepository.save(net2);

            // 3. Beispiel: Bereits geborgenes Netz (User Story 4)
            GhostNet net3 = new GhostNet();
            net3.setStandort("54.500, 11.200 (Fehmarn)");
            net3.setGroesse("ca. 5m Treibnetz");
            net3.setStatus(Status.GEBORGEN);
            ghostNetRepository.save(net3);
        }
    }
}