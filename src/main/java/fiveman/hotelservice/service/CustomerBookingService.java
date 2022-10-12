package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Customer;

import java.util.List;

public interface CustomerBookingService {
    List<Customer> getAllCustomerByBooking_Id(long id);
}

