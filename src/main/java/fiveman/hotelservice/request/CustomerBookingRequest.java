package fiveman.hotelservice.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerBookingRequest {
    private long id;
    private long customer_Id;
    private long booking_Id;
    private String primaryCustomer;
}
