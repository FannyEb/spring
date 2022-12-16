package net.eicnam.fip1.ptt.backend.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @JsonProperty("users")
    @OneToMany(mappedBy = "jid_im")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<RoomUser> roomUsers;
}
