package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface BookingService {
    Booking getBookingById(long id);

    List<Booking> getAllBooking();

    CustomResponseObject saveBooking(Booking booking);

    CustomResponseObject updateBooking(Booking booking);

    CustomResponseObject deleteBooking(long id);

}
