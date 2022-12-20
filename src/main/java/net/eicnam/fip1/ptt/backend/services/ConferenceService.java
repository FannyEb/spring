package net.eicnam.fip1.ptt.backend.services;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.eicnam.fip1.sdk.rainbow.conferences.Conferences;

@AllArgsConstructor
@Service
public class ConferenceService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConferenceService.class);

    public void startConference(final String id) {
        LOGGER.info("startConference() > Starting conference for room id: {}", id);
        final Conferences conference = new Conferences();
        conference.startConference(id);
    }
}
