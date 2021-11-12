package de.cogamemonolith.event.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Main entity class which contains simple information to the event such as id, name, date and time
 * and other entity classes such as Address and Activity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@ApiModel(description = "Contains all information about a specific event, including users id, who participates in the event")
public class Event {

    @Id
    private String id;

    @ApiModelProperty(notes = AttributeDescription.name)
    @Size(min = AttributeDescription.nameSize, message = AttributeDescription.name)
    private String name;

    private String description;

    @Future(message = AttributeDescription.dateAndTime)
    @ApiModelProperty(notes = AttributeDescription.dateAndTime)
    private LocalDateTime dateTimeOfEvent;

    //min number of the participants needed for the event
    // includes the creator
    @Min(value = AttributeDescription.participantsNumberMin, message = AttributeDescription.participantsNumber)
    @ApiModelProperty(notes = AttributeDescription.participantsNumber)
    private int participantsNumber;

    @NotEmpty(message = AttributeDescription.creatorUserId)
    @ApiModelProperty(notes = AttributeDescription.creatorUserId)
    private String creatorUserId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Valid
    private Address placeAddress;

    @NotNull
    @Size(min = AttributeDescription.activitiesSize, message = AttributeDescription.activities)
    @ApiModelProperty(notes = AttributeDescription.activities)
    private List<@Valid Activity> activities;

    @Valid
    @ApiModelProperty(notes = AttributeDescription.participants)
    private Map<@NotBlank(message = "Participant id should not be empty") String,
            @NotBlank(message = "Participant name should not be empty") String> participants;



    private List<Tool> tools;


}
