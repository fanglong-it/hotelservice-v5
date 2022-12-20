package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.request.BookingRequest;
import fiveman.hotelservice.request.CheckInRequest;
import fiveman.hotelservice.request.Statistic;
import fiveman.hotelservice.response.BookingObjectResponse;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.DashboardResponse;
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

    // @GetMapping("/booking")
    // @PreAuthorize("isAnonymous() or isAuthenticated()")
    // public ResponseEntity<List<BookingObjectResponse>>
    // getAllBookingByRoomId(@RequestParam("room_id") long room_id) {
    // return new
    // ResponseEntity<List<BookingObjectResponse>>(bookingService.getAllBookingByRoomId(room_id),
    // HttpStatus.OK);
    // }

    @GetMapping("/bookings")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<Booking>> getAllBooking() {
        return new ResponseEntity<>(bookingService.getAllBooking(), HttpStatus.OK);
    }

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/booking")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<Booking> saveBooking(@RequestBody BookingRequest bookingRequest) {
        Booking booking = modelMapper.map(bookingRequest, Booking.class);
        return new ResponseEntity<>(bookingService.saveBooking(booking), HttpStatus.OK);
    }

    @PutMapping("/booking")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<Booking> updateBooking(@RequestBody BookingRequest bookingRequest) {
        Booking booking = modelMapper.map(bookingRequest, Booking.class);
        return new ResponseEntity<>(bookingService.updateBooking(booking), HttpStatus.OK);
    }

    @DeleteMapping("/booking/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> deleteBooking(@PathVariable("id") long id) {
        return new ResponseEntity<>(bookingService.deleteBooking(id), HttpStatus.OK);
    }

    @PostMapping("/booking/checkIn")
    public ResponseEntity<CustomResponseObject> checkInBooking(@RequestBody CheckInRequest checkInRequest) {
        return new ResponseEntity<>(bookingService.checkInBooking(checkInRequest), HttpStatus.OK);
    }

    @PostMapping("/booking/checkInAtHotel")
    public ResponseEntity<CustomResponseObject> checkInAtHotel(@RequestBody CheckInRequest checkInRequest) {
        return new ResponseEntity<>(bookingService.checkInAtHotel(checkInRequest), HttpStatus.OK);
    }

    @PostMapping("/booking/checkOut")
    public ResponseEntity<CustomResponseObject> checkOutBooking(@RequestParam("booking_id") long bookingId) {
        return new ResponseEntity<>(bookingService.checkOutBooking(bookingId), HttpStatus.OK);
    }

    @PostMapping("/booking/customerNoShow")
    public ResponseEntity<CustomResponseObject> customerNoShow(@RequestParam("booking_id") long bookingId) {
        return new ResponseEntity<>(bookingService.customerNoShow(bookingId), HttpStatus.OK);
    }

    @PostMapping("/booking/cancleBooking")
    public ResponseEntity<CustomResponseObject> cancleBooking(@RequestParam("booking_id") long bookingId) {
        return new ResponseEntity<>(bookingService.cancleBooking(bookingId), HttpStatus.OK);
    }

    @GetMapping("/booking/dashboardBetween")
    public ResponseEntity<DashboardResponse> getDashBoard(@RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        return new ResponseEntity<>(bookingService.getDashBoard(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/booking/bookingByRoomId")
    public ResponseEntity<Booking> getBookingByRoomId(@RequestParam("room_id") long room_id) {
        return new ResponseEntity<>(bookingService.getBookingByRoomId(room_id), HttpStatus.OK);
    }

    @GetMapping("/booking/bookingByCustomerId")
    public ResponseEntity<Booking> getBookingByCustomerId(@RequestParam("customer_id") long customer_id) {
        return new ResponseEntity<>(bookingService.getBookingByCustomerId(customer_id), HttpStatus.OK);
    }

    @GetMapping("/booking/revenuesEntire")
    public ResponseEntity<List<Statistic>> getRevenuesEntire(@RequestParam("dateStart") String dateStart,
            @RequestParam("dateEnd") String dateEnd) {
        return new ResponseEntity<>(bookingService.getRevenuesEntireDate(dateStart, dateEnd), HttpStatus.OK);
    }

    @GetMapping("/booking/revenuesCancelEntire")
    public ResponseEntity<List<Statistic>> getRevenuesCancelEntire(@RequestParam("dateStart") String dateStart,
            @RequestParam("dateEnd") String dateEnd) {
        return new ResponseEntity<>(bookingService.getRevenuesCancelEntireDate(dateStart, dateEnd), HttpStatus.OK);
    }

}
