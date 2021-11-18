package de.cogamemonolith.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Contains all information to the user
 *
 */
@AllArgsConstructor
@NoArgsConstructor
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

    private String name;
    private LocalDate dateOfBirth;
    private String sex;
    private String phoneNumber;

    @ManyToOne
    private PlaceOfLiving placeOfLiving;
    @ManyToOne
    private Occupation occupation;
    @OneToOne
    private Account account;
    @ManyToMany
    Set<Event> events;

//    Optional attributes, which are needed for completing of personal account
    @ElementCollection
    private List<String> favouriteBooks;
    @ElementCollection
    private List<String> favouriteMovies;
    @ElementCollection
    private List<String> favouriteGames;
    @ElementCollection
    private List<String> favouriteMusic;

}
