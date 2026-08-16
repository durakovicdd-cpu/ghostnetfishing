package de.iu.ghostnetfishing;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // 5. Formular für die Verschollen-Meldung anzeigen (User Story 7 mit Kontaktdaten-Pflicht)
    @GetMapping("/ghostnets/{id}/report-missing")
    public String showReportMissingForm(@PathVariable("id") Long id, Model model) {
        MissingReportForm form = new MissingReportForm();
        form.setNetId(id);
        model.addAttribute("missingForm", form);
        return "missing-form";
    }

    // 6. Verschollen-Meldung verarbeiten
    @PostMapping("/ghostnets/report-missing")
    public String processReportMissing(
            @Valid @ModelAttribute("missingForm") MissingReportForm form,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "missing-form";
        }

        Optional<GhostNet> optionalNet = ghostNetRepository.findById(form.getNetId());
        if (optionalNet.isPresent()) {
            GhostNet net = optionalNet.get();

            // Meldende Person mit Pflichtangaben speichern
            Person reporter = new Person(form.getReporterName(), form.getReporterPhone());
            personRepository.save(reporter);

            // Status aktualisieren
            net.setStatus(Status.VERSCHOLLEN);
            ghostNetRepository.save(net);

            redirectAttributes.addFlashAttribute("successMessage", "Netz wurde erfolgreich als verschollen gemeldet.");
        }

        return "redirect:/";
    }
}