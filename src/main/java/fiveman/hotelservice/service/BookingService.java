package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.response.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse getBookingById(long id);

    List<BookingResponse> getAllBooking();

    List<BookingResponse> getAllBookingByRoomId(long id);

    List<BookingResponse> saveBooking(Booking booking);

    List<BookingResponse> updateBooking(Booking booking);

    List<BookingResponse> deleteBooking(long id);

}
