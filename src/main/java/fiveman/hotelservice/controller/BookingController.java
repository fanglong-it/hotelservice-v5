package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.request.BookingRequest;
import fiveman.hotelservice.request.CheckInRequest;
import fiveman.hotelservice.response.BookingObjectResponse;
import fiveman.hotelservice.response.CheckInResponse;
import fiveman.hotelservice.service.BookingService;
import io.swagger.annotations.Api;
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
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<BookingObjectResponse> getBookingById(@PathVariable("id") long id) {
        return new ResponseEntity<>(bookingService.getBookingById(id), HttpStatus.OK);
    }

    @GetMapping("/booking")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<BookingObjectResponse>> getBookingByRoomId(@RequestParam("room_id") long room_id){
        return new ResponseEntity<List<BookingObjectResponse>>(bookingService.getAllBookingByRoomId(room_id), HttpStatus.OK);
    }


    @GetMapping("/bookings")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<BookingObjectResponse>> getAllBooking() {
        return new ResponseEntity<>(bookingService.getAllBooking(), HttpStatus.OK);
    }

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/booking")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<BookingObjectResponse>> saveBooking(@RequestBody BookingRequest bookingRequest) {
        Booking booking = modelMapper.map(bookingRequest, Booking.class);
        return new ResponseEntity<>(bookingService.saveBooking(booking), HttpStatus.OK);
    }

    @PutMapping("/booking")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<BookingObjectResponse>> updateBooking(@RequestBody BookingRequest bookingRequest) {
        Booking booking = modelMapper.map(bookingRequest, Booking.class);
        return new ResponseEntity<>(bookingService.updateBooking(booking), HttpStatus.OK);
    }

    @DeleteMapping("/booking/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<BookingObjectResponse>> deleteBooking(@PathVariable("id") long id) {
        return new ResponseEntity<>(bookingService.deleteBooking(id), HttpStatus.OK);
    }

    @PostMapping("/booking/checkIn")
    public ResponseEntity<CheckInResponse> checkInBooking(@RequestBody CheckInRequest checkInRequest){
        return new ResponseEntity<>(bookingService.checkInBooking(checkInRequest), HttpStatus.OK);
    }

    @PostMapping("/booking/checkOut")
    public ResponseEntity<BookingObjectResponse> checkOutBooking(@RequestParam("booking_id") long bookingId){
        return new ResponseEntity<>(bookingService.checkOutBooking(bookingId), HttpStatus.OK);
    }

}
