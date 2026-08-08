package de.iu.ghostnetfishing;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class GhostNet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String standort; // z. B. GPS-Koordinaten "54.1234, 10.5678"
    private String groesse;  // z. B. geschätzte Größe in Metern "5m x 2m"

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Person bergendePerson; // Die Person, die das Netz bergen möchte (kann null sein)

    // Leerer Konstruktor für JPA
    public GhostNet() {
    }

    public GhostNet(String standort, String groesse, Status status) {
        this.standort = standort;
        this.groesse = groesse;
        this.status = status;
    }

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStandort() {
        return standort;
    }

    public void setStandort(String standort) {
        this.standort = standort;
    }

    public String getGroesse() {
        return groesse;
    }

    public void setGroesse(String groesse) {
        this.groesse = groesse;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Person getBergendePerson() {
        return bergendePerson;
    }

    public void setBergendePerson(Person bergendePerson) {
        this.bergendePerson = bergendePerson;
    }
}