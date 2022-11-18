package net.eicnam.fip1.ptt.backend.services;

import java.util.Collection;

import net.eicnam.fip1.ptt.backend.models.RUser;
import net.eicnam.fip1.ptt.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.eicnam.fip1.ptt.backend.models.Room;
import net.eicnam.fip1.sdk.rainbow.bubbles.Bubbles;
import net.eicnam.fip1.ptt.backend.repositories.RoomRepository;

@AllArgsConstructor
@Service
public class RoomService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomService.class);

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public Collection<Room> getAllByUser(final String token) {
        final Bubbles bubbles = new Bubbles();

        final ObjectMapper objectMapper = new ObjectMapper();
        Collection<Room> rooms = null;
        try {
            final RUser rUser = userRepository.findById(token).orElseThrow();

            final String jsonRooms = objectMapper.readTree(bubbles.getAllUserBubbles(rUser.getId())).get("data").toString();
            rooms = objectMapper.readValue(jsonRooms, new TypeReference<>() {});
            roomRepository.saveAll(rooms);
        } catch (final JsonProcessingException e) {
            LOGGER.error("getAll() > Error while parsing response to JSON: {}", e.getMessage());
        }
        return rooms;
    }

    public Room getById(final String id) {
        return roomRepository.findById(id).orElse(null);
    }
}
