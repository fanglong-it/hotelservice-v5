package fiveman.hotelservice.response;

import java.util.List;

import fiveman.hotelservice.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckInResponse {
    private BookingObjectResponse bookingObjectResponse;
    private List<Customer> customers;
}
