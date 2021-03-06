package de.cogamemonolith.web.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Contains information to the message of an event
 * A Message should be assigned to an event and an user
 */
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Message request DTO-Object for responding on GET messages/... ")
@Data
public class MessageResponse {

    private Long id;

    @ApiModelProperty(notes = "Id of the user who posted the message. Should not be empty")
    private UserResponse user;


    @ApiModelProperty(notes = "Message itself")
    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt = LocalDateTime.now();
}
