package de.cogamemonolith.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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

    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceOfLiving placeOfLiving;
    @ManyToOne(cascade = CascadeType.ALL)
    private Occupation occupation;
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && name.equals(user.name) && dateOfBirth.equals(user.dateOfBirth) && sex.equals(user.sex) && phoneNumber.equals(user.phoneNumber) && placeOfLiving.equals(user.placeOfLiving) && occupation.equals(user.occupation) && account.equals(user.account) && favouriteBooks.equals(user.favouriteBooks) && favouriteMovies.equals(user.favouriteMovies) && favouriteGames.equals(user.favouriteGames) && favouriteMusic.equals(user.favouriteMusic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, sex, phoneNumber, placeOfLiving, occupation, account, favouriteBooks, favouriteMovies, favouriteGames, favouriteMusic);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", sex='" + sex + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", placeOfLiving=" + placeOfLiving +
                ", occupation=" + occupation +
                ", account=" + account +
                ", favouriteBooks=" + favouriteBooks +
                ", favouriteMovies=" + favouriteMovies +
                ", favouriteGames=" + favouriteGames +
                ", favouriteMusic=" + favouriteMusic +
                '}';
    }

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
