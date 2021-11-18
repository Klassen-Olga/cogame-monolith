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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/*
 * Model class for user's authentication
 * */
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Contains authentication data: login and password")
@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Email(message = AttributeDescription.email)
    @NotBlank
    @ApiModelProperty(notes = AttributeDescription.email)
    private String email;

    @Size(min = AttributeDescription.passwordSize, message = AttributeDescription.password)
    @ApiModelProperty(notes = AttributeDescription.password)
    private String password;


}
