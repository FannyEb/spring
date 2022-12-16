package net.eicnam.fip1.ptt.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.eicnam.fip1.ptt.backend.models.RoomUser;

public interface RoomUserRepository extends JpaRepository<RoomUser, String> {
}
