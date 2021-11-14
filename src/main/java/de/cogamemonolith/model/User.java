package de.cogamemonolith.model;

import de.cogamemonolith.validation.EnumValidation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

/**
 * Main entity class which contains all information to the user
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Contains user details for the cogame project")
@Entity
@Data
@Table(name="co_user")
public class User {
    public enum Sex {
        MALE,
        FEMALE,
        OTHER
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(min = UserAttributeDescription.userNameSize, message = UserAttributeDescription.userName)
    @ApiModelProperty(notes =  UserAttributeDescription.userName)
    private String name;

    @Past(message = UserAttributeDescription.dateOdBirth)
    @ApiModelProperty(notes = UserAttributeDescription.dateOdBirth)
    private LocalDate dateOdBirth;

    @EnumValidation(enumClass = Sex.class, message = UserAttributeDescription.sex)
    @ApiModelProperty(notes = UserAttributeDescription.sex)
    private String sex;

    @ApiModelProperty(notes= UserAttributeDescription.phoneNumber)
    @Size(min = UserAttributeDescription.phoneNumberSize, message = UserAttributeDescription.phoneNumber)
    private String phoneNumber;

    @Valid
    @ManyToOne
    private PlaceOfLiving placeOfLiving;
    @ManyToOne
    @Valid
    private Occupation occupation;

    @Valid
    @OneToOne
    private Account account;
    @OneToMany
    List<Event> events;

//    Optional attributes, which are needed for completing of personal account
    @ApiModelProperty(notes= UserAttributeDescription.preferencesList)
    @ElementCollection
    private List<String> favouriteBooks;
    @ApiModelProperty(notes= UserAttributeDescription.preferencesList)
    @ElementCollection
    private List<String> favouriteMovies;
    @ApiModelProperty(notes= UserAttributeDescription.preferencesList)
    @ElementCollection
    private List<String> favouriteGames;
    @ApiModelProperty(notes= UserAttributeDescription.preferencesList)
    @ElementCollection
    private List<String> favouriteMusic;

}
