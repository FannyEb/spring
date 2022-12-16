package net.eicnam.fip1.ptt.backend.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Entity
public class RoomUser
{
    @Id
    @JsonProperty("jid_im")
    private String jid_im;

    @JsonProperty("userId")
    private String userId;
}
