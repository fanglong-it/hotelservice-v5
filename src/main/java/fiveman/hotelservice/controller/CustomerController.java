package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.Customer;
import fiveman.hotelservice.service.CustomerService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Customer")
@RequestMapping("/api/v1")
public class CustomerController {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CustomerService customerService;

    @GetMapping("/customer/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @GetMapping("/customers")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @PostMapping("/customer")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<Customer>> saveCustomer(@RequestBody @Valid Customer customer) {
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.OK);
    }

    @PutMapping("/customer")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<Customer>> updateCustomer(@RequestBody @Valid Customer customer) {
        return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<Customer>> deleteCustomer(@PathVariable("id") long id) {
        return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }

    @GetMapping("/getPrimaryCustomerByBookingId")
    public ResponseEntity<Customer> getPrimaryCustomerByBookingId(@RequestParam("booking_id") long booking_id) {
        return new ResponseEntity<>(customerService.getPrimaryCustomerByBookingId(booking_id), HttpStatus.OK);
    }

    @GetMapping("/getAllPrimaryCustomer")
    public ResponseEntity<List<Customer>> getAllPrimary() {
        return new ResponseEntity<>(customerService.getAllPrimaryCustomer(), HttpStatus.OK);
    }

}
