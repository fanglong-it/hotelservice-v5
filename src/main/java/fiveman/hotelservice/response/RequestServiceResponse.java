package fiveman.hotelservice.response;

import fiveman.hotelservice.entities.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestServiceResponse {
    private long id;
    private String requestServiceName;
    private String requestServiceType;
    private String dateTime;
    private String status;
    private Booking booking;
}
