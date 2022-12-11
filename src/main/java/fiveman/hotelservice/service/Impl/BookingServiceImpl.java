package fiveman.hotelservice.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fiveman.hotelservice.entities.*;
import fiveman.hotelservice.repository.*;
import fiveman.hotelservice.response.DashboardResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.request.BookingRequest;
import fiveman.hotelservice.request.CheckInRequest;
import fiveman.hotelservice.request.CustomerRequest;
import fiveman.hotelservice.response.BookingObjectResponse;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.security.JwtTokenProvider;
import fiveman.hotelservice.service.BookingService;
import fiveman.hotelservice.utils.Common;
import fiveman.hotelservice.utils.Utilities;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    public BookingObjectResponse mapBookingToResponse(Booking booking) {
        // BookingResponse bookingResponse = new BookingResponse();
        // bookingResponse.setId(booking.getId());
        // bookingResponse.setConfirmationNo(booking.getConfirmationNo());
        // bookingResponse.setArrivalDate(booking.getArrivalDate());
        // bookingResponse.setActualArrivalDate(booking.getActualArrivalDate());
        // bookingResponse.setDepartureDate(booking.getDepartureDate());
        // bookingResponse.setActualDepartureDate(booking.getActualDepartureDate());
        // bookingResponse.setNumOfAdult(booking.getNumOfAdult());
        // bookingResponse.setNumOfChildren(booking.getNumOfChildren());
        // bookingResponse.setTotalAmount(booking.getTotalAmount());
        // bookingResponse.setRoomPayment(booking.getRoomPayment());
        // bookingResponse.setSpecialNote(booking.getSpecialNote());
        // bookingResponse.setStatus(booking.getStatus());
        // bookingResponse.setCreateDate(booking.getCreateDate());
        // bookingResponse.setUpdateDate(booking.getUpdateDate());
        // bookingResponse.setCreateBy(booking.getCreateBy());
        // bookingResponse.setLastModifyBy(booking.getLastModifyBy());
        // bookingResponse.setRoomTypeId(booking.getRoomTypeId());
        // bookingResponse.setHotel_Id(booking.getHotel().getId());
        // bookingResponse.setRoom_Id(booking.getRoom().getId());
        // bookingResponse.setCustomer(booking.getCustomer());
        // bookingResponse.setRequestServices(booking.getRequestServices());
        // return bookingResponse;

        // ModelMap
        BookingObjectResponse bookingResponse = modelMapper.map(booking, BookingObjectResponse.class);
        return bookingResponse;
    }

    @Override
    public BookingObjectResponse getBookingById(long id) {
        if (!bookingRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                    new CustomResponseObject(Common.GET_FAIL, "Not found id =" + id));
        }
        return mapBookingToResponse(bookingRepository.getBookingById(id));
    }

    @Override
    public List<BookingObjectResponse> getAllBooking() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingObjectResponse> bookingResponses = new ArrayList<>();
        for (Booking booking : bookings) {
            // booking.setHotel(hotelRepository.getHotelById(booking.getHotel().getId()));
            // booking.setRoom(roomRepository.getById(booking.getRoom().getId()));
            BookingObjectResponse bookingResponse = mapBookingToResponse(booking);

            bookingResponse.setHotel_Id(booking.getHotel().getId());
            bookingResponse.setRoomPayment(booking.getRoomPayment());

            if (bookingResponse.getStatus().equals(Common.BOOKING_CHECKIN)) {
                bookingResponse.setRoom_Id(booking.getRoom().getId());
            }
            bookingResponses.add(bookingResponse);
        }
        return bookingResponses;
    }

    @Override
    public List<BookingObjectResponse> saveBooking(Booking booking) {
        if (bookingRepository.existsById(booking.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                    new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + booking.getId()));
        }
        bookingRepository.save(booking);
        // return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Success!");
        return getAllBooking();
    }

    @Override
    public List<BookingObjectResponse> updateBooking(Booking booking) {
        if (!bookingRepository.existsById(booking.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                    new CustomResponseObject(Common.UPDATE_FAIL, "Not found id =" + booking.getId()));
        }
        bookingRepository.save(booking);
        // return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
        return getAllBooking();
    }

    @Override
    public List<BookingObjectResponse> deleteBooking(long id) {
        if (!bookingRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                    new CustomResponseObject(Common.DELETE_FAIL, "Not found id =" + id));
        }
        bookingRepository.deleteById(id);
        // return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
        return getAllBooking();
    }

    @Override
    public List<BookingObjectResponse> getAllBookingByRoomId(long id) {
        // return bookingRepository.getAllBookingsByRoomId(id);
        List<Booking> bookings = bookingRepository.getAllBookingsByRoomIdAndStatus(id, Common.BOOKING_CHECKIN);
        List<BookingObjectResponse> bookingResponses = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingObjectResponse bookingResponse = mapBookingToResponse(booking);
            bookingResponse.setCustomerStayBooking(customerRepository.getCustomerStayBooking(booking.getId()));
            bookingResponses.add(bookingResponse);
        }
        return bookingResponses;
    }

    @Autowired
    CustomerBookingRepository customerBookingRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @Override
    public Booking getBookingByCustomerId(long customer_id) {

        return bookingRepository.getBookingByCustomerId(customer_id);
    }

    @Override
    @Transactional
    public CustomResponseObject checkInBooking(CheckInRequest checkInRequest) {
        BookingRequest bookingRequest = checkInRequest.getBookingRequest();
        List<CustomerRequest> customerRequests = checkInRequest.getCustomerRequests();
        Booking booking = modelMapper.map(bookingRequest, Booking.class);
        if (booking.getStatus().equals(Common.BOOKING_BOOKED)) {
            // getCurrent Date time
            String currentDateTime = Utilities.getCurrentDateByFormat("dd/MM/YYYY HH:mm:ss");
            booking.setActualDepartureDate(null);
            booking.setActualArrivalDate(currentDateTime);
            booking.setUpdateDate(Utilities.getCurrentDateByFormat("dd/MM/YYYY HH:mm:ss"));
            booking.setLastModifyBy(bookingRequest.getLastModifyBy());
            booking.setStatus(Common.BOOKING_CHECKIN);
            booking.setCustomer(customerRepository.getCustomerById(bookingRequest.getCustomer_Id()));
            booking.setHotel(hotelRepository.getHotelById(bookingRequest.getHotel_Id()));
            booking.setRoom(roomRepository.getRoomById(bookingRequest.getRoom_Id()));
            booking.setRoomPayment(bookingRequest.getRoomPayment());

            // Save Booking
            bookingRepository.save(booking);
            booking = bookingRepository.getBookingById(bookingRequest.getId());

            // Set Status of Room To False
            Room room = booking.getRoom();
            room.setStatus(true);
            roomRepository.save(room);

            // Check if Occupancy is Available for Customer Stay
            RoomType roomType = roomTypeRepository.getRoomTypeById(booking.getRoomTypeId());
            if (roomType.getMaxOccupancy() >= customerRequests.size()) {
                List<Customer> customers = new ArrayList<>();
                for (CustomerRequest customerRequest : customerRequests) {
                    Customer customer = modelMapper.map(customerRequest, Customer.class);
                    customers.add(customer);
                    customerRepository.save(customer);
                    customer = customerRepository.findTopByOrderByIdDesc();
                    CustomerBooking customerBooking = new CustomerBooking(0, customer, booking,
                            null);
                    if (customerRequest.isPrimary()) {
                        customerBooking.setPrimaryCustomer(String.valueOf(customer.getId()));
                    } else {
                        customerBooking.setPrimaryCustomer(null);
                    }
                    customerBookingRepository.save(customerBooking);
                }

            } else {
                throw new AppException(HttpStatus.BAD_REQUEST.value(),
                        new CustomResponseObject(Common.UPDATE_FAIL, "Can't Check in because of Max Occupancy!"));
            }
        } else {
            throw new AppException(HttpStatus.BAD_REQUEST.value(),
                    new CustomResponseObject(Common.UPDATE_FAIL, "Can't Check in because of Status!"));
        }
        return new CustomResponseObject(Common.ADDING_SUCCESS, "Check In Success!");
    }

    @Autowired
    RoomPriceRepository roomPriceRepository;

    @Override
    public CustomResponseObject checkOutBooking(long bookingId
    // , HttpServletRequest request
    ) {
        Booking booking = bookingRepository.getBookingById(bookingId);

        if (booking.getStatus().equals(Common.BOOKING_CHECKIN)) { // Status is Check In
            String currentDateTime = Utilities.getCurrentDateByFormat("dd/MM/yyyy HH:mm:ss");
            booking.setActualDepartureDate(currentDateTime);
            booking.setStatus(Common.BOOKING_CHECKOUT);
            List<Order> listOrder = booking.getOrders();

            boolean isPayment = true;
            for (Order order : listOrder) { // Check if Order is not Payment
                if (order.getOrderPayment() == null) {
                    isPayment = false;
                }
            }

            if (isPayment) { // Booking not Payment
                Room room = roomRepository.getRoomById(booking.getRoom().getId());
                room.setStatus(false);
                roomRepository.save(room); // Turn on status of room

                RoomType roomType = roomTypeRepository.getRoomTypeById(booking.getRoomTypeId());
                roomType.setMaxBookingRoom(roomType.getMaxBookingRoom() + 1); // Set Default of BookingRoom In
                roomTypeRepository.save(roomType);

                if (booking.getRoomPayment().equals("N/A") || Utilities.isEmptyString(booking.getRoomPayment())) { // Check
                    String today = Utilities.getCurrentDateByFormat("dd/MM/yyyy");
                    RoomPrice roomPrice = roomPriceRepository.getRoomPriceTodayByRoomType(today,
                            booking.getRoomTypeId());
                    if (roomPrice == null) {
                        booking.setRoomPayment(String.valueOf(roomType.getDefaultPrice()));
                    } else {
                        booking.setRoomPayment(String.valueOf(roomPrice.getPrice()));
                    }
                }

                // getTotal Amount
                double totalAmount = 0; // Total Of Booking
                double orderAmount = 0; // Total Of Order
                for (Order order : listOrder) {
                    orderAmount += Utilities.calculateTotalAmount(order.getOrderDetails());
                }
                totalAmount = Double.parseDouble(booking.getRoomPayment()) + orderAmount; // Plus Booking Payment Price
                                                                                          // and Order Amount
                booking.setTotalAmount(totalAmount);
                bookingRepository.save(booking);
                booking = bookingRepository.getBookingById(bookingId);
            } else {
                throw new AppException(HttpStatus.BAD_REQUEST.value(),
                        new CustomResponseObject(Common.GET_FAIL, "Can't Checkout please Payment!"));
            }
        }
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Check Out Success!");
    }

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public CustomResponseObject customerNoShow(long bookingId
    // , HttpServletRequest request
    ) {

        Booking booking = bookingRepository.getBookingById(bookingId);
        if (booking == null || !booking.getStatus().equals(Common.BOOKING_BOOKED)) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(),
                    new CustomResponseObject(Common.UPDATE_FAIL, "Can't Set Status to NO SHOW"));
        }
        Date todayDate;
        Date bookingDate;
        try {
            todayDate = new SimpleDateFormat(Common.DATE).parse(Utilities.getCurrentDateByFormat("dd/MM/yyyy"));
            bookingDate = new SimpleDateFormat(Common.DATE).parse(booking.getArrivalDate());
        } catch (ParseException e) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(),
                    new CustomResponseObject(Common.UPDATE_FAIL, "Can't Parse Date"));
        }

        if (todayDate.before(bookingDate)) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(),
                    new CustomResponseObject(Common.UPDATE_FAIL, "Can't Set If today is before check in!"));
        }

        booking.setStatus(Common.BOOKING_NOT_SHOW);
        // String username =
        // jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request));
        // booking.setLastModifyBy(username);
        booking.setUpdateDate(Utilities.getCurrentDate());
        bookingRepository.save(booking);

        // Set again RoomType
        RoomType roomType = roomTypeRepository.getRoomTypeById(booking.getRoomTypeId());
        roomType.setMaxBookingRoom(roomType.getMaxBookingRoom() + 1);
        roomTypeRepository.save(roomType);
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Change to NO SHOW success!");
    }

    @Override
    public DashboardResponse getDashBoard(String date) {
        DashboardResponse data = new DashboardResponse();
        data.setBookedToday(bookingRepository.getBookedToday(date));
        data.setAccumulateRevenue(bookingRepository.getRevenueInMonthByCurrentDate(date) != null
                ? bookingRepository.getRevenueInMonthByCurrentDate(date)
                : "");
        data.setActualArriveToday(bookingRepository.getActualArriveDay(date));
        data.setRevenue(bookingRepository.getRevenueCurrentDate(date) != null
                ? bookingRepository.getRevenueInMonthByCurrentDate(date)
                : "");
        data.setCanceledToday(bookingRepository.getCancelToday(date));
        data.setCancelRevenue(bookingRepository.getCancelRevenueCurrentDate(date) != null
                ? bookingRepository.getCancelRevenueInMonthByCurrentDate(date)
                : "");
        data.setCancelAccumulateRevenue(bookingRepository.getCancelRevenueInMonthByCurrentDate(date) != null
                ? bookingRepository.getCancelRevenueInMonthByCurrentDate(date)
                : "");
        data.setRoomBusy(bookingRepository.getCheckInToday(date));
        data.setActualDepartureToday(bookingRepository.getActualDepartureDay(date));
        data.setNumOfStay(bookingRepository.getAllCustomerStay());
        data.setBookingList(bookingRepository.getRevenueEntireMonth(date));
        return data;
    }

    @Override
    public Booking getBookingByRoomId(long room_id) {
        Booking booking = bookingRepository.getBookingByRoomId(room_id);
        if (booking == null) {
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                    new CustomResponseObject(Common.GET_FAIL, "Not exist Booking with room_id = " + room_id));
        }
        return booking;
    }

}
