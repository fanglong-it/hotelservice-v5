package fiveman.hotelservice.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpecialRequestRequest {
      @ApiModelProperty(required = true)
      private long id;
      
      @NotNull
      private long booking_Id;
      
      @NotNull
      private long specialUtility_Id;
}   
