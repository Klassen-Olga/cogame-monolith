package de.cogamemonolith.web.dto.in;


import de.cogamemonolith.model.Activity;
import de.cogamemonolith.model.Address;
import de.cogamemonolith.model.AttributeDescription;
import de.cogamemonolith.model.Tool;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Contains simple information to the event such as id, name, date and time
 * and other entity classes such as Address and Activity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Event request DTO-Object for creating and updating Event entity")
public class EventCreateRequest {


    @ApiModelProperty(notes = AttributeDescription.name)
    @Size(min = AttributeDescription.nameSize, message = AttributeDescription.name)
    private String name;

    private String description;

    @Future(message = AttributeDescription.dateAndTime)
    @ApiModelProperty(notes = AttributeDescription.dateAndTime)
    private LocalDateTime dateTimeOfEvent;

    @NotNull
    private Long creatorId;

    @Valid
    private Address placeAddress;

    @NotNull
    @Size(min = AttributeDescription.activitiesSize, message = AttributeDescription.activities)
    @ApiModelProperty(notes = AttributeDescription.activities)
    private List<@Valid Activity> activities;

    private List<Tool> tools;


}
