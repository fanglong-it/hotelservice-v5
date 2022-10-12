package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.Customer;
import fiveman.hotelservice.entities.CustomerBooking;
import fiveman.hotelservice.repository.CustomerBookingRepository;
import fiveman.hotelservice.repository.CustomerRepository;
import fiveman.hotelservice.service.CustomerBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerBookingServiceImpl implements CustomerBookingService {
    @Autowired
    CustomerBookingRepository customerBookingRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomerByBooking_Id(long id) {
        List<CustomerBooking> customerBookings = customerBookingRepository.getAllByBooking_Id(id);
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < customerBookings.size(); i++) {
            customers.add(customerRepository.getCustomerById(customerBookings.get(i).getCustomer().getId()));
        }
        return customers;
    }

}
