package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.request.CheckInRequest;
import fiveman.hotelservice.response.BookingObjectResponse;
import fiveman.hotelservice.response.CheckInResponse;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.DashboardResponse;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface BookingService {
    BookingObjectResponse getBookingById(long id);

    List<BookingObjectResponse> getAllBooking();

    List<BookingObjectResponse> getAllBookingByRoomId(long id);

    List<BookingObjectResponse> saveBooking(Booking booking);

    List<BookingObjectResponse> updateBooking(Booking booking);

    List<BookingObjectResponse> deleteBooking(long id);

    CheckInResponse checkInBooking(CheckInRequest checkInRequest);

    BookingObjectResponse checkOutBooking(long bookingId
    // , HttpServletRequest request
    );

    CustomResponseObject customerNotShow(long bookingId
    // , HttpServletRequest request
    );

    DashboardResponse getDashBoard(String date);

}
