package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.request.BookingRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.BookingService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Booking")
@RequestMapping("/api/v1")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @GetMapping("/booking/{id}")
    @PreAuthorize("isAnonymous() or hasRole('ROLE_USER')")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") long id) {
        return new ResponseEntity<>(bookingService.getBookingById(id), HttpStatus.OK);
    }

    @GetMapping("/booking")
    @PreAuthorize("isAnonymous() or hasRole('ROLE_USER')")
    public ResponseEntity<List<Booking>> getAllBooking() {
        return new ResponseEntity<>(bookingService.getAllBooking(), HttpStatus.OK);
    }

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/booking")
    @PreAuthorize("isAnonymous() or hasRole('ROLE_USER')")
    public ResponseEntity<CustomResponseObject> saveBooking(@RequestBody BookingRequest bookingRequest) {
        Booking booking = modelMapper.map(bookingRequest, Booking.class);
        return new ResponseEntity<>(bookingService.saveBooking(booking), HttpStatus.OK);
    }

    @PutMapping("/booking")
    @PreAuthorize("isAnonymous() or hasRole('ROLE_USER')")
    public ResponseEntity<CustomResponseObject> updateBooking(@RequestBody BookingRequest bookingRequest) {
        Booking booking = modelMapper.map(bookingRequest, Booking.class);
        return new ResponseEntity<>(bookingService.updateBooking(booking), HttpStatus.OK);
    }

    @DeleteMapping("/booking/{id}")
    @PreAuthorize("isAnonymous() or hasRole('ROLE_USER')")
    public ResponseEntity<CustomResponseObject> deleteBooking(@PathVariable("id") long id) {
        return new ResponseEntity<>(bookingService.deleteBooking(id), HttpStatus.OK);
    }

}
