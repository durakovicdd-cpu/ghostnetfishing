# Ghost Net Fishing App

Prototyp zur Erfassung und Verwaltung von Geisternetzen im Rahmen der IU Fallstudie Softwareentwicklung.

## Umgesetzte Anforderungen

Im ersten Sprint wurden die folgenden fünf User Stories aus dem Product Backlog umgesetzt:

1. **User Story 1 (Geisternetz melden):** Anonyme Erfassung neuer Geisternetze mit Standort und geschätzter Größe.
2. **User Story 2 (Für Bergung eintragen):** Bergende Personen können sich mit Name und Telefonnummer für ein offenes Netz eintragen.
3. **User Story 3 (Offene Netze anzeigen):** Übersicht aller noch zu bergenden Netze mit dem Status GEMELDET.
4. **User Story 4 (Als geborgen melden):** Statusänderung eines Netzes auf GEBORGEN.
5. **User Story 7 (Als verschollen melden):** Statusänderung eines Netzes auf VERSCHOLLEN.

## Technologiestack

* **Sprache & Framework:** Java, Spring Boot
* **Datenbank & Persistenz:** Spring Data JPA, H2 In-Memory Database
* **Frontend:** Thymeleaf, HTML5, CSS3
* **Build-Tool:** Maven

## Projektstruktur

* `GhostnetfishingApplication.java`: Hauptklasse zum Starten der Anwendung
* `DataInitializer.java`: Initialisiert Beispieldaten beim Anwendungsstart
* `GhostNetController.java`: Controller für Routing und Formularverarbeitung
* `GhostNet.java` / `Person.java` / `Status.java`: Datenmodell (Entities & Enum)
* `GhostNetRepository.java` / `PersonRepository.java`: JPA-Datenbankschnittstellen
* `src/main/resources/templates/`: HTML-Views (`index.html`, `melden.html`)

## Anwendung starten

1. Die Anwendung über die Hauptklasse `GhostnetfishingApplication.java` ausführen.
2. Im Webbrowser die Adresse `http://localhost:8080` aufrufen.

Beim Start werden über die Klasse `DataInitializer` automatisch drei Beispieldatensätze angelegt.