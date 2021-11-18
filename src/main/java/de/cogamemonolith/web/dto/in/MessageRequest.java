package de.cogamemonolith.web.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.cogamemonolith.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * Contains information to the message of an event
 * A Message should be assigned to an event and an user
 */
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Message request DTO-Object for creating and updating Message entity")
@Data
public class MessageRequest {

    private Long id;

    @NotEmpty(message="User id can not be empty")
    @ApiModelProperty(notes = "Id of the user who posted the message. Should not be empty")
    private User user;


    @ApiModelProperty(notes = "Message itself")
    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt = LocalDateTime.now();
}
