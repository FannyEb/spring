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
<<<<<<< HEAD:src/main/java/net/eicnam/fip1/ptt/backend/models/RoomUser.java
    @JsonProperty("jid_im")
    private String jid_im;
=======
    @JsonProperty("id")
    private String id;
>>>>>>> 033db29d2cca918880a9ce03919bd07d4ac2839f:src/main/java/net/eicnam/fip1/ptt/backend/models/Conference.java

    @JsonProperty("userId")
    private String userId;
}
