package fiveman.hotelservice.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@AllArgsConstructor
@Data
@Getter
public class RequestServiceRequest {
      @ApiModelProperty(position = 0, required = true)
      private long id;
      
      private String requestServiceName;
      private String dateTime;
      private boolean status;
      
      @NotNull
      private long booking_Id;
}
