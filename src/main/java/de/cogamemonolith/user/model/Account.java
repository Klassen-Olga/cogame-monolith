package de.cogamemonolith.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/*
 * Model class for user's authentication
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Contains authentication data: login and password")
public class Account {

    @Email(message = AttributeDescription.email)
    @NotBlank
    @ApiModelProperty(notes = AttributeDescription.email)
    @Indexed(unique = true)
    private String email;

    @Size(min = AttributeDescription.passwordSize, message = AttributeDescription.password)
    @ApiModelProperty(notes = AttributeDescription.password)
    private String password;


}
