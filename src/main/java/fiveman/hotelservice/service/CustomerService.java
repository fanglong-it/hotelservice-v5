package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Customer;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(long id);

    List<Customer> getAllCustomers();

    CustomResponseObject saveCustomer(Customer customer);

    CustomResponseObject updateCustomer(Customer customer);

    CustomResponseObject deleteCustomer(long id);

}
