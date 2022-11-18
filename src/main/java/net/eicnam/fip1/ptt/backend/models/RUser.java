package net.eicnam.fip1.ptt.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class RUser {
    public RUser() {
    }

    @Id
    @Column(columnDefinition="TEXT", length = 1000)
    @JsonProperty("token")
    private String token;

    @JsonProperty("id")
    private String id;
}
