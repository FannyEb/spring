package net.eicnam.fip1.ptt.backend.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.eicnam.fip1.sdk.rainbow.bubbles.Bubbles;

import net.eicnam.fip1.ptt.backend.models.Room;
import net.eicnam.fip1.ptt.backend.repositories.UserRepository;
import net.eicnam.fip1.ptt.backend.models.RUser;
import net.eicnam.fip1.ptt.backend.models.RoomUser;
import net.eicnam.fip1.ptt.backend.repositories.RoomRepository;
import net.eicnam.fip1.ptt.backend.response.RoomsResponse;

@AllArgsConstructor
@Service
public class RoomService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomService.class);

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final ConferenceService conferenceService;

    public Collection<RoomsResponse> getAllByUser(final String token) {

        final RUser rUser = userRepository.findById(token).orElseThrow();

        final Bubbles bubbles = new Bubbles();
        Collection<Room> allRooms = null;
        try {
            final String response = bubbles.getAll();

            final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            final JsonNode jsonNode = objectMapper.readTree(response);
            final String jsonRooms = jsonNode.get("data").toString();
            allRooms = objectMapper.readValue(jsonRooms, new TypeReference<>() {});
            roomRepository.saveAll(allRooms);
        } catch (final JsonProcessingException e) {
            LOGGER.error("getAll() > Error while parsing response to JSON: {}", e.getMessage());
        }

        if (allRooms == null) {
            return null;
        }

        final Collection<RoomsResponse> availableRooms = new ArrayList<>();
        for (final Room room : allRooms) {
            final List<RoomUser> roomUsers = room.getRoomUsers();
            for (final RoomUser roomUser : roomUsers) {
                final String userId = roomUser.getUserId();

                if (userId.equals(rUser.getId())) {
                    availableRooms.add(new RoomsResponse(room));
                }
            }
        }

        return availableRooms;
    }

    public Room getById(final String id) {
        return roomRepository.findById(id).orElse(null);
    }

    private String getRoomConferenceIdOrNull(final String id) {
        final Bubbles bubbles = new Bubbles();
        String response = bubbles.getById(id);

        String conferenceId = null;
        // Get data from response
        final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            // Parse response | data -> conference -> sessions[0] -> id
            JsonNode jsonNode = objectMapper.readTree(response)
                    .get("data")
                    .get("conference")
                    .get("sessions");
            if (jsonNode.isArray() && jsonNode.size() > 0) {
                conferenceId = jsonNode.get(0).get("id").asText();
            }

        } catch (final JsonProcessingException e) {
            LOGGER.error("getRoomConferenceIdOrNull() > Error while parsing response to JSON: {}", e.getMessage());
        }
        return conferenceId;
    }

    public String getConferenceId(final String id) {

        final String conferenceId = getRoomConferenceIdOrNull(id);
        if (conferenceId == null) {
            // Start conference
            conferenceService.startConference(id);
            // Then return conference Id
            return getRoomConferenceIdOrNull(id);
        }

        // Return conference Id
        return conferenceId;
    }
}
