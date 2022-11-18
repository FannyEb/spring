package net.eicnam.fip1.ptt.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.eicnam.fip1.ptt.backend.models.Room;

public interface RoomRepository extends JpaRepository<Room, String> {
}
