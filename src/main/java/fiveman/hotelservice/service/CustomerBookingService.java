package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Customer;
import fiveman.hotelservice.entities.CustomerBooking;
import fiveman.hotelservice.response.CustomerBookingResponse;

import java.util.List;

public interface CustomerBookingService {
    List<CustomerBookingResponse> getAllCustomerBookingResponse();
    List<Customer> getAllCustomerByBooking_Id(long id);
    List<CustomerBookingResponse> saveCustomerBooking(CustomerBooking customerBooking);
}


