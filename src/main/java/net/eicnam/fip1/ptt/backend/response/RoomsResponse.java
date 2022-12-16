package net.eicnam.fip1.ptt.backend.response;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.eicnam.fip1.ptt.backend.models.Room;

@Getter
@Setter
public class RoomsResponse
{
    public RoomsResponse(final Room room)
    {
        this.id = room.getId();
        this.name = room.getName();
        this.jid = room.getJid();
        this.lastActivityDate = room.getLastActivityDate();
        this.isAlertNotificationEnabled = room.getIsAlertNotificationEnabled();
        this.isActive = room.isActive();
    }

    @JsonProperty("id")
    protected String id;

    @JsonProperty("name")
    protected String name;

    @JsonProperty("jid")
    protected String jid;

    @JsonProperty("lastActivityDate")
    protected String lastActivityDate;

    @JsonProperty("isAlertNotificationEnabled")
    protected String isAlertNotificationEnabled;

    @JsonProperty("isActive")
    protected boolean isActive;
}
