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

    @Email(message = UserAttributeDescription.email)
    @NotBlank
    @ApiModelProperty(notes = UserAttributeDescription.email)
    private String email;

    @Size(min = UserAttributeDescription.passwordSize, message = UserAttributeDescription.password)
    @ApiModelProperty(notes = UserAttributeDescription.password)
    private String password;


}
