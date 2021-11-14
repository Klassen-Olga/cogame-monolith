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
* Model describes user's address
* */
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Contains user's address")
@Entity
@Data
public class PlaceOfLiving {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(min = UserAttributeDescription.citySize, message = UserAttributeDescription.city)
    @ApiModelProperty(notes = UserAttributeDescription.city)
    private String city;

    @Size(min = UserAttributeDescription.countrySize, message = UserAttributeDescription.country)
    @ApiModelProperty(notes = UserAttributeDescription.country)
    private String country;




}
