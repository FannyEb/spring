package net.eicnam.fip1.ptt.backend.repositories;

import net.eicnam.fip1.ptt.backend.models.RUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<RUser, String> {
}
