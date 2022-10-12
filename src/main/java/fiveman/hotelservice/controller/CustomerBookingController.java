package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.Customer;
import fiveman.hotelservice.service.CustomerBookingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "Customer_Booking")
@RequestMapping("/api/v1")
public class CustomerBookingController {
    @Autowired
    CustomerBookingService customerBookingService;

    @GetMapping("/customerBooking/{id}")
    @PreAuthorize("isAnonymous() or hasRole('ROLE_USER')")
    public ResponseEntity<List<Customer>> getAllCustomerByBooking_Id(@PathVariable("id") long id) {
        return new ResponseEntity<>(customerBookingService.getAllCustomerByBooking_Id(id), HttpStatus.OK);
    }

}
