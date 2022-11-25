package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.request.CheckInRequest;
import fiveman.hotelservice.response.BookingObjectResponse;
import fiveman.hotelservice.response.CheckInResponse;

import java.util.List;

public interface BookingService {
    BookingObjectResponse getBookingById(long id);

    List<BookingObjectResponse> getAllBooking();

    List<BookingObjectResponse> getAllBookingByRoomId(long id);

    List<BookingObjectResponse> saveBooking(Booking booking);

    List<BookingObjectResponse> updateBooking(Booking booking);

    List<BookingObjectResponse> deleteBooking(long id);

    CheckInResponse checkInBooking(CheckInRequest checkInRequest);

    BookingObjectResponse checkOutBooking(long bookingId);

}
