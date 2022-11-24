package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(long id);

    List<Customer> getAllCustomers();

    List<Customer> saveCustomer(Customer customer);

    List<Customer> updateCustomer(Customer customer);

    List<Customer> deleteCustomer(long id);

}
