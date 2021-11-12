package de.cogamemonolith.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/*
 * Contains details to user's occupation: place and art of occupation
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Contains details to user's occupation: place and art of occupation")
public class Occupation {

    @Size(min = AttributeDescription.occupationNameSize, message = AttributeDescription.occupationName)
    @ApiModelProperty(notes = AttributeDescription.occupationName)
    private String occupationName;


    @Size(min = AttributeDescription.placeOfOccupationSize, message = AttributeDescription.placeOfOccupation)
    @ApiModelProperty(notes = AttributeDescription.placeOfOccupation)
    private String placeOfOccupation;


}
