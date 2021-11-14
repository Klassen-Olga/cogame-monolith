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
    @Size(min = UserAttributeDescription.occupationNameSize, message = UserAttributeDescription.occupationName)
    @ApiModelProperty(notes = UserAttributeDescription.occupationName)
    private String occupationName;


    @Size(min = UserAttributeDescription.placeOfOccupationSize, message = UserAttributeDescription.placeOfOccupation)
    @ApiModelProperty(notes = UserAttributeDescription.placeOfOccupation)
    private String placeOfOccupation;


}
