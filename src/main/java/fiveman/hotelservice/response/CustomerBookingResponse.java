package fiveman.hotelservice.response;

import fiveman.hotelservice.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerBookingResponse {
    private long id;
    private Customer customer;
    private long booking_Id;
    private String primaryCustomer;
}
