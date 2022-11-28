package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.Customer;
import fiveman.hotelservice.entities.CustomerBooking;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.CustomerBookingRepository;
import fiveman.hotelservice.repository.CustomerRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.CustomerBookingResponse;
import fiveman.hotelservice.service.CustomerBookingService;
import fiveman.hotelservice.utils.Common;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerBookingServiceImpl implements CustomerBookingService {
    @Autowired
    CustomerBookingRepository customerBookingRepository;

    @Autowired
    CustomerRepository customerRepository;


    @Autowired
    ModelMapper modelMapper;

    public CustomerBookingResponse mapCustomerBookingToResponse(CustomerBooking customerBooking){
        CustomerBookingResponse customerBookingResponse = modelMapper.map(customerBooking, CustomerBookingResponse.class);
        customerBookingResponse.setBooking_Id(customerBooking.getBooking().getId());
        return customerBookingResponse;
    }

    @Override
    public List<Customer> getAllCustomerByBooking_Id(long id) {
        List<CustomerBooking> customerBookings = customerBookingRepository.getAllByBooking_Id(id);
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < customerBookings.size(); i++) {
            customers.add(customerRepository.getCustomerById(customerBookings.get(i).getCustomer().getId()));
        }
        return customers;
    }

    

    @Override
    public List<CustomerBookingResponse> getAllCustomerBookingResponse() {
        List<CustomerBooking> customerBookings = customerBookingRepository.findAll();
        List<CustomerBookingResponse> customerBookingResponses = new ArrayList<>();
        for (CustomerBooking customerBooking : customerBookings) {
            CustomerBookingResponse customerBookingResponse = mapCustomerBookingToResponse(customerBooking);
            customerBookingResponses.add(customerBookingResponse);
        }
        return customerBookingResponses;
    }

    // private long id;
    // private String firstName;
    // private String middleName;
    // private String lastName;
    // private int gender;
    // private String phoneNumber;
    // private String email;
    // private int idNo;
    // private int passportNo;
    
    // private String birthDate;

    // private String createDate;
    // private String updateDate;
    // private String createBy;
    // private String lastModifyBy;

    @Override
    public List<CustomerBookingResponse> saveCustomerBooking(CustomerBooking customerBooking) {
        

        if(customerBookingRepository.existsById(customerBooking.getId())){
           throw new AppException(HttpStatus.ALREADY_REPORTED, new CustomResponseObject(Common.ADDING_FAIL, "Exist id = " + customerBooking.getId()));
        }    
        customerBookingRepository.save(customerBooking);    
        return getAllCustomerBookingResponse();
        // return null;
    }

    

}
