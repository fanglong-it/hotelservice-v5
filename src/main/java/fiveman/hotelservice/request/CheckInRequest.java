package fiveman.hotelservice.request;

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
public class CheckInRequest {
    private BookingRequest bookingRequest;
    private List<Customer> customer;
}

