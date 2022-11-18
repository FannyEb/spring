package net.eicnam.fip1.ptt.backend.repositories;

import net.eicnam.fip1.ptt.backend.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {
}
