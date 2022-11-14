package fiveman.hotelservice.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@AllArgsConstructor
@Getter
@Setter
public class RoomAlarmRequest {
      
      @ApiModelProperty(required = true)
      private long id;
      
      private String dateTime;
      
      private boolean status;

      private long booking_Id;
}
