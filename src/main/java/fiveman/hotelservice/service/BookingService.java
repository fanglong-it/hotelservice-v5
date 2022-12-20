package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.request.CheckInRequest;
import fiveman.hotelservice.request.Statistic;
import fiveman.hotelservice.response.BookingObjectResponse;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.DashboardResponse;
import java.util.List;

public interface BookingService {

    BookingObjectResponse getBookingById(long id);

    List<Booking> getAllBooking();

    List<BookingObjectResponse> getAllBookingByRoomId(long id);

    Booking getBookingByRoomId(long room_id);

    Booking getBookingByCustomerId(long customer_id);

    Booking saveBooking(Booking booking);

    Booking updateBooking(Booking booking);

    CustomResponseObject deleteBooking(long id);

    CustomResponseObject checkInBooking(CheckInRequest checkInRequest);

    CustomResponseObject checkInAtHotel(CheckInRequest checkInRequest);

    CustomResponseObject checkOutBooking(long bookingId
    // , HttpServletRequest request
    );

    CustomResponseObject cancleBooking(long booking_id);

    CustomResponseObject customerNoShow(long bookingId
    // , HttpServletRequest request
    );

    DashboardResponse getDashBoard(String startDate, String endDate);

    List<Statistic> getRevenuesEntireDate(String dateStart, String dateEnd);

    List<Statistic> getRevenuesCancelEntireDate(String dateStart, String dateEnd);
}
