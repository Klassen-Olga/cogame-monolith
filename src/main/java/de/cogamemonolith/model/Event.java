package de.cogamemonolith.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Contains simple information to the event such as id, name, date and time
 * and other entity classes such as Address and Activity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String description;
    private LocalDateTime dateTimeOfEvent;
    private Integer maximalNumberOfParticipants;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address placeAddress;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<@Valid Activity> activities;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Tool> tools;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Message> messages;
    @ManyToOne
    private User creator;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> participants;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt = LocalDateTime.now();


}
