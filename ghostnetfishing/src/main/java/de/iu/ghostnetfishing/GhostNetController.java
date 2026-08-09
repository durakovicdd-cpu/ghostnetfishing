package de.iu.ghostnetfishing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class GhostNetController {

    private final GhostNetRepository ghostNetRepository;
    private final PersonRepository personRepository;

    public GhostNetController(GhostNetRepository ghostNetRepository, PersonRepository personRepository) {
        this.ghostNetRepository = ghostNetRepository;
        this.personRepository = personRepository;
    }

    // 1. Startseite: Zeigt offene Netze und die Gesamtübersicht (User Story 3)
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("offeneNetze", ghostNetRepository.findByStatus(Status.GEMELDET));
        model.addAttribute("alleNetze", ghostNetRepository.findAll());
        return "index";
    }

    // 2. Formular-Seite zum Erfassen eines neuen Netzes (User Story 1)
    @GetMapping("/melden")
    public String zeigenMeldenFormular(Model model) {
        model.addAttribute("ghostNet", new GhostNet());
        return "melden";
    }

    // Formulardaten verarbeiten und Netz speichern (User Story 1)
    @PostMapping("/melden")
    public String netzMelden(@ModelAttribute GhostNet ghostNet) {
        ghostNet.setStatus(Status.GEMELDET);
        ghostNetRepository.save(ghostNet);
        return "redirect:/";
    }

    // 3. Für die Bergung eintragen (User Story 2)
    @PostMapping("/bergung/{id}")
    public String bergungAnmelden(@PathVariable Long id, @RequestParam String name, @RequestParam String telefonnummer) {
        Optional<GhostNet> optionalNet = ghostNetRepository.findById(id);
        if (optionalNet.isPresent()) {
            GhostNet net = optionalNet.get();
            Person person = new Person(name, telefonnummer);
            personRepository.save(person);

            net.setBergendePerson(person);
            net.setStatus(Status.BERGUNG_BEVORSTEHEND);
            ghostNetRepository.save(net);
        }
        return "redirect:/";
    }

    // 4. Netz als geborgen melden (User Story 4)
    @PostMapping("/geborgen/{id}")
    public String alsGeborgenMelden(@PathVariable Long id) {
        Optional<GhostNet> optionalNet = ghostNetRepository.findById(id);
        if (optionalNet.isPresent()) {
            GhostNet net = optionalNet.get();
            net.setStatus(Status.GEBORGEN);
            ghostNetRepository.save(net);
        }
        return "redirect:/";
    }

    // 5. Netz als verschollen melden (User Story 7)
    @PostMapping("/verschollen/{id}")
    public String alsVerschollenMelden(@PathVariable Long id) {
        Optional<GhostNet> optionalNet = ghostNetRepository.findById(id);
        if (optionalNet.isPresent()) {
            GhostNet net = optionalNet.get();
            net.setStatus(Status.VERSCHOLLEN);
            ghostNetRepository.save(net);
        }
        return "redirect:/";
    }
}