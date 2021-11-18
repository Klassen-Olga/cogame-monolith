package de.cogamemonolith.web.dto.out;

import de.cogamemonolith.model.*;
import de.cogamemonolith.validation.EnumValidation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * Contains all information to the user
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "User request DTO-Object for responding on GET users/... ")
@Data
public class UserResponse {
    public enum Sex {
        MALE,
        FEMALE,
        OTHER
    }

    private Long id;

    @Size(min = AttributeDescription.userNameSize, message = AttributeDescription.userName)
    @ApiModelProperty(notes =  AttributeDescription.userName)
    private String name;

    @Past(message = AttributeDescription.dateOdBirth)
    @ApiModelProperty(notes = AttributeDescription.dateOdBirth)
    private LocalDate dateOfBirth;

    @EnumValidation(enumClass = User.Sex.class, message = AttributeDescription.sex)
    @ApiModelProperty(notes = AttributeDescription.sex)
    private String sex;

    @ApiModelProperty(notes= AttributeDescription.phoneNumber)
    @Size(min = AttributeDescription.phoneNumberSize, message = AttributeDescription.phoneNumber)
    private String phoneNumber;

    @Valid
    private PlaceOfLiving placeOfLiving;
    @Valid
    private Occupation occupation;
    @Valid
    private Account account;

    //    Optional attributes, which are needed for completing of personal account
    @ApiModelProperty(notes= AttributeDescription.preferencesList)
    private List<String> favouriteBooks;
    @ApiModelProperty(notes= AttributeDescription.preferencesList)
    private List<String> favouriteMovies;
    @ApiModelProperty(notes= AttributeDescription.preferencesList)
    private List<String> favouriteGames;
    @ApiModelProperty(notes= AttributeDescription.preferencesList)
    private List<String> favouriteMusic;

}