package net.eicnam.fip1.ptt.backend.repositories;

import net.eicnam.fip1.ptt.backend.models.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference, String> {

}
