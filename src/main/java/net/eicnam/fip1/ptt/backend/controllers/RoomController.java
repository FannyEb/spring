package net.eicnam.fip1.ptt.backend.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;

import net.eicnam.fip1.ptt.backend.services.RoomService;
import net.eicnam.fip1.ptt.backend.models.Room;
import static net.eicnam.fip1.ptt.backend.utils.Namespace.JSON;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class RoomController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);

    private final RoomService roomService;

    @GetMapping(value = "/rooms", produces = JSON)
    public Collection<Room> getAll() {
        LOGGER.info("Request incoming: retrieve all Rooms");
        return roomService.getAll();
    }

    @GetMapping(value = "/room/{id}", produces = JSON)
    public Room getById(@PathVariable final String id) {
        LOGGER.info("Request incoming: retrieve Room with id {}", id);
        return roomService.getById(id);
    }

}
