package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.Customer;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.CustomerRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.CustomerService;
import fiveman.hotelservice.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;



    
    @Override
    public Customer getPrimaryCustomerByBookingId(long booking_id) {
        Customer customer = customerRepository.getPrimaryCustomer(booking_id);
        if(customer == null){
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Not found id Customer by booking = " + booking_id));
        }
        return customer;
    }

    

    @Override
    public List<Customer> getAllPrimaryCustomer() {
        return customerRepository.getAllPrimaryCustomer();
    }

    



    @Override
    public List<Customer> getAllCustomerByBookingId(long booking_id) {
        return customerRepository.getAllCustomerByBooking(booking_id);
    }



    @Override
    public Customer getCustomerById(long id) {
        if (!customerRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Not found id =" + id));
        }
        return customerRepository.getCustomerById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> saveCustomer(Customer customer) {
        if (customerRepository.existsById(customer.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + customer.getId()));
        }
        customerRepository.save(customer);
        // return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Success!");
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> updateCustomer(Customer customer) {
        if (!customerRepository.existsById(customer.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id =" + customer.getId()));
        }
        customerRepository.save(customer);
        // return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> deleteCustomer(long id) {
        if (!customerRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Not found id =" + id));
        }
        customerRepository.deleteById(id);
        // return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
        return customerRepository.findAll();
    }
}
