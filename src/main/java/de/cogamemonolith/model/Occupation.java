package de.cogamemonolith.model;

import de.cogamemonolith.model.AttributeDescription;
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

/*
 * Contains details to user's occupation: place and art of occupation
 * */
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Contains details to user's occupation: place and art of occupation")
@Entity
@Data
public class Occupation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Size(min = AttributeDescription.occupationNameSize, message = AttributeDescription.occupationName)
    @ApiModelProperty(notes = AttributeDescription.occupationName)
    private String occupationName;


    @Size(min = AttributeDescription.placeOfOccupationSize, message = AttributeDescription.placeOfOccupation)
    @ApiModelProperty(notes = AttributeDescription.placeOfOccupation)
    private String placeOfOccupation;


}
