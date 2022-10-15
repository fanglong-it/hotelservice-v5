package fiveman.hotelservice.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class SpecialRequestRequest {
      @ApiModelProperty(required = true)
      private long id;
      
      @NotNull
      private long booking_Id;
      
      @NotNull
      private long specialUtility_Id;
}   
