package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Customer;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface CustomerService {

    Customer getCustomerById(long id);
    Customer getPrimaryCustomerByBookingId(long booking_id);
    List<Customer> getAllPrimaryCustomer();
    List<Customer> getAllCustomerByBookingId(long booking_id);
    List<Customer> getAllCustomers();
    List<Customer> saveCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    CustomResponseObject deleteCustomer(long id);

}
