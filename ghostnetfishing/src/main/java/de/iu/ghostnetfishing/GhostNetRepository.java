package de.iu.ghostnetfishing;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GhostNetRepository extends JpaRepository<GhostNet, Long> {

    // Filtert automatisch alle Netze nach einem bestimmten Status (z. B. GEMELDET)
    List<GhostNet> findByStatus(Status status);
}