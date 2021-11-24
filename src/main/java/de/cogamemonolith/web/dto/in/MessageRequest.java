package de.cogamemonolith.web.dto.in;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contains information to the message of an event
 * A Message should be assigned to an event and an user
 */
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Message request DTO-Object for creating and updating Message entity")
@Data
public class MessageRequest {


    @ApiModelProperty(notes = "Id of the user who posted the message. Should not be empty")
    private Long userId;


    @ApiModelProperty(notes = "Message itself")
    private String text;


}
