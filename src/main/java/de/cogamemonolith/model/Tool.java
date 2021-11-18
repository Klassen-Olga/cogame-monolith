package de.cogamemonolith.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Contains information to a tool, that is required for a certain event e.g. Ball, Beer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Contains information to a tool, that is required for a certain event e.g. Ball, Beer")
@Entity
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String toolName;
    private boolean alreadyExists;
}
