package de.cogamemonolith.model;

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
 * Represents address information where the event should take place
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Represents address information where the event should take place")
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Size(min = EventAttributeDescription.streetSize, message = EventAttributeDescription.street)
    @ApiModelProperty(notes = EventAttributeDescription.street)
    private String streetAndHomeNumber;

    @Size(min = EventAttributeDescription.citySize, message = EventAttributeDescription.city)
    @ApiModelProperty(notes = EventAttributeDescription.city)
    private String city;

    @Size(min = EventAttributeDescription.countrySize, message = EventAttributeDescription.country)
    @ApiModelProperty(notes = EventAttributeDescription.country)
    private String country;
}
