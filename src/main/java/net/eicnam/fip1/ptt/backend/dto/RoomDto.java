package net.eicnam.fip1.ptt.backend.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomDto implements Serializable {
    private final boolean active;
    private final String name;
    private final String jid;
    private final String id;
}
