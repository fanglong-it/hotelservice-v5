package fiveman.hotelservice.request;

import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class MessageRequest {
      
      @Id
      @ApiModelProperty(required = true)
      private long id;

      private String messageContent;

      private long booking_Id;

}
