package de.cogamemonolith.model;

import de.cogamemonolith.validation.EnumValidation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * Represents activity unit of the event
 * important features: art of the activity and activity name
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Contains information about the activities of the event")
@Entity
public class Activity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    public enum ActivityArt {
        COMPUTER,
        TABLE,
        ACTIVE

    }

    @EnumValidation(enumClass = ActivityArt.class, message = EventAttributeDescription.activityArt)
    @ApiModelProperty(notes =  EventAttributeDescription.activityArt)
    private String activityArt;

    @Size(min= EventAttributeDescription.activityNameSize, message = EventAttributeDescription.activityName)
    @ApiModelProperty(notes =  EventAttributeDescription.activityName)
    private String name;
}

