package de.cogamemonolith.web.dto.out;


import com.fasterxml.jackson.annotation.JsonFormat;
import de.cogamemonolith.model.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Contains simple information to the event such as id, name, date and time
 * and other entity classes such as Address and Activity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Event request DTO-Object for responding on GET users/... ")
public class EventResponse {


    private Long id;

    @ApiModelProperty(notes = AttributeDescription.name)
    @Size(min = AttributeDescription.nameSize, message = AttributeDescription.name)
    private String name;

    private String description;

    @Future(message = AttributeDescription.dateAndTime)
    @ApiModelProperty(notes = AttributeDescription.dateAndTime)
    private LocalDateTime dateTimeOfEvent;
    @Valid
    private UserResponse creator;
    @Valid
    private Address placeAddress;

    @NotNull
    @Size(min = AttributeDescription.activitiesSize, message = AttributeDescription.activities)
    @ApiModelProperty(notes = AttributeDescription.activities)
    private List<@Valid Activity> activities;

    private List<Tool> tools;
    private List< @Valid Message> messages;
    private Set<@Valid User> participants;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt = LocalDateTime.now();


}
