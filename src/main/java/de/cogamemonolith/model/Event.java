package de.cogamemonolith.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Main entity class which contains simple information to the event such as id, name, date and time
 * and other entity classes such as Address and Activity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Contains all information about a specific event, including users id, who participates in the event")
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ApiModelProperty(notes = EventAttributeDescription.name)
    @Size(min = EventAttributeDescription.nameSize, message = EventAttributeDescription.name)
    private String name;

    private String description;

    @Future(message = EventAttributeDescription.dateAndTime)
    @ApiModelProperty(notes = EventAttributeDescription.dateAndTime)
    private LocalDateTime dateTimeOfEvent;

    //min number of the participants needed for the event
    // includes the creator
    @Min(value = EventAttributeDescription.participantsNumberMin, message = EventAttributeDescription.participantsNumber)
    @ApiModelProperty(notes = EventAttributeDescription.participantsNumber)
    private int participantsNumber;

    @NotEmpty(message = EventAttributeDescription.creatorUserId)
    @ApiModelProperty(notes = EventAttributeDescription.creatorUserId)
    private String creatorUserId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Valid
    @ManyToOne
    private Address placeAddress;

    @NotNull
    @Size(min = EventAttributeDescription.activitiesSize, message = EventAttributeDescription.activities)
    @ApiModelProperty(notes = EventAttributeDescription.activities)
    @ManyToMany
    private List<@Valid Activity> activities;
    @OneToMany
    private List<Tool> tools;
    @OneToMany
    private List<Message> messages;
    @OneToOne
    private User creator;


}
