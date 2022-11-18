package net.eicnam.fip1.ptt.backend.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.*;

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
    public Collection<Room> getAll(@RequestHeader String Authorization) {
        LOGGER.info("Request incoming: retrieve all Rooms by user");

        final String[] token = Authorization.split(" ");

        if (token.length != 2) {
            return null;
        }
        if (!token[0].equals("Bearer")) {
            return null;
        }

        return roomService.getAllByUser(token[1]);
    }

    @GetMapping(value = "/room/{id}", produces = JSON)
    public Room getById(@PathVariable final String id) {
        LOGGER.info("Request incoming: retrieve Room with id {}", id);
        return roomService.getById(id);
    }

}
