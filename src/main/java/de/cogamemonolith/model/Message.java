package de.cogamemonolith.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * Main entity class which contains information to the message of an event
 * A Message should be assigned to an event and an user
 */
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Contains a message-resource for a specific event from a specific user")
@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty(message="User id can not be empty")
    @ApiModelProperty(notes = "Id of the user who posted the message. Should not be empty")
    private String userId;

    @NotEmpty(message="User name can not be empty")
    @ApiModelProperty(notes = "Name of the user who posted the message. Should not be empty")
    private String userName;

    @NotEmpty(message="Event id can not be empty")
    @ApiModelProperty(notes = "Id of the event, to which the message is referring")
    private String eventId;

    @ApiModelProperty(notes = "Message itself")
    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt = LocalDateTime.now();
}
