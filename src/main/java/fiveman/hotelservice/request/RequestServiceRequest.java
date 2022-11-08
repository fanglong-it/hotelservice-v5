package fiveman.hotelservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@AllArgsConstructor
@Getter
@Setter
public class RequestServiceRequest {
      private long id;
      private String requestServiceName;
      private String requestServiceType;
      private String dateTime;
      private String status;
      private long booking_Id;
}
