package net.eicnam.fip1.ptt.backend.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Entity
public class Room {

    @JsonProperty("isActive")
    private boolean isActive;

    @JsonProperty("name")
    private String name;

    @JsonProperty("jid")
    private String jid;

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("lastActivityDate")
    private String lastActivityDate;

    @JsonProperty("isAlertNotificationEnabled")
    private String isAlertNotificationEnabled;
}
