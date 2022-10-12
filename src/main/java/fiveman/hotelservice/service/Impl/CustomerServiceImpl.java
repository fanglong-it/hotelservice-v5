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
    public CustomResponseObject saveCustomer(Customer customer) {
        if (customerRepository.existsById(customer.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + customer.getId()));
        }
        customerRepository.save(customer);
        return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Success!");
    }

    @Override
    public CustomResponseObject updateCustomer(Customer customer) {
        if (!customerRepository.existsById(customer.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id =" + customer.getId()));
        }
        customerRepository.save(customer);
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
    }

    @Override
    public CustomResponseObject deleteCustomer(long id) {
        if (!customerRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Not found id =" + id));
        }
        customerRepository.deleteById(id);
        return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
    }
}
