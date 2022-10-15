package fiveman.hotelservice.request;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class CustomerFeedbackRequest {

      @Id
      @ApiModelProperty(required = true)
      private long id;

      @NotNull
      private long booking_Id;
      
      private String rating;
      private String dateTime;
      private long feedbackContent_Id;
      
      
}
