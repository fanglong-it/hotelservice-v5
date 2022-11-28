package fiveman.hotelservice.service.Impl;

import java.util.ArrayList;
import java.util.List;

import fiveman.hotelservice.entities.*;
import fiveman.hotelservice.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.request.BookingRequest;
import fiveman.hotelservice.request.CheckInRequest;
import fiveman.hotelservice.response.BookingObjectResponse;
import fiveman.hotelservice.response.BookingResponse;
import fiveman.hotelservice.response.CheckInResponse;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.BookingService;
import fiveman.hotelservice.utils.Common;
import fiveman.hotelservice.utils.Utilities;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;

    // private long id;
    // private int confirmationNo;
    // private String arrivalDate;
    // private String actualArrivalDate;
    // private String departureDate;
    // private String actualDepartureDate;
    // private int numOfAdult;
    // private int numOfChildren;
    // private double totalAmount;
    // private String roomPayment;
    // private String specialNote;
    // private String status;
    // private String createDate;
    // private String updateDate;
    // private String createBy;
    // private String lastModifyBy;
    // private long roomTypeId;
    // private long hotel_Id;
    // private long room_Id;
    // private Customer customer;
    // private List<RequestService> requestServices;

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
            BookingObjectResponse bookingResponse = mapBookingToResponse(booking);
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

        List<Booking> bookings = bookingRepository.getAllBookingsByRoomIdAndStatus(id, "Check In");
        List<BookingObjectResponse> bookingResponses = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingObjectResponse bookingResponse = mapBookingToResponse(booking);
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
    public CheckInResponse checkInBooking(CheckInRequest checkInRequest) {

        BookingRequest bookingRequest = checkInRequest.getBookingRequest();

        Booking booking = modelMapper.map(bookingRequest, Booking.class);
        List<Customer> customers = checkInRequest.getCustomer();
        if (booking.getStatus().equals(Common.BOOKING_BOOKED)) {
            // getCurrent Date time
            String currentDateTime = Utilities.getCurrentDateByFormat("dd/MM/YYYY HH:mm:ss");
            booking.setActualArrivalDate(currentDateTime);
            booking.setUpdateDate(Utilities.getCurrentDateByFormat("dd/MM/YYYY HH:mm:ss"));
            booking.setStatus(Common.BOOKING_CHECKIN);
            booking.setCustomer(customerRepository.getCustomerById(bookingRequest.getCustomer_Id()));
            booking.setHotel(hotelRepository.getHotelById(bookingRequest.getHotel_Id()));
            booking.setRoom(roomRepository.getRoomById(bookingRequest.getRoom_Id()));
            booking.setRoomPayment(bookingRequest.getRoomPayment());
            bookingRepository.save(booking);
            booking = bookingRepository.getBookingById(bookingRequest.getId());

            if (booking.getRoom().getRoomType().getMaxOccupancy() > customers.size()) {
                for (Customer customer : customers) {
                    customer.setId(0);
                    customerRepository.save(customer);
                    Customer newCustomer = customerRepository.findTopByOrderByIdDesc();
                    customer = newCustomer;
                    CustomerBooking customerBooking = new CustomerBooking(0, newCustomer, booking,
                            booking.getCustomer().getLastName());
                    customerBookingRepository.save(customerBooking);
                }
            }
        }
        CheckInResponse checkInResponse = new CheckInResponse();
        checkInResponse.setCustomers(customers);
        checkInResponse.setBookingObjectResponse(modelMapper.map(booking, BookingObjectResponse.class));
        return checkInResponse;
    }

    @Override
    public BookingObjectResponse checkOutBooking(long bookingId) {
        Booking booking = bookingRepository.getBookingById(bookingId);

        if (booking.getStatus().equals(Common.BOOKING_CHECKIN)) { //Status is Check In
            String currentDateTime = Utilities.getCurrentDateByFormat("dd/MM/yyyy HH:mm:ss");
            booking.setActualDepartureDate(currentDateTime);
            booking.setStatus(Common.BOOKING_CHECKOUT);
            List<Order> listOrder = booking.getOrders();
            
            boolean isPayment = true;
            for (Order order : listOrder) { //Check if Order is not Payment
                if (order.getOrderPayment() == null) {
                    isPayment = false;
                }
            }

            if (booking.getRoomPayment().equals("N/A") || !isPayment) { // Booking not Payment
                throw new AppException(HttpStatus.BAD_REQUEST.value(),
                        new CustomResponseObject(Common.GET_FAIL, "Can't Checkout please Payment!"));
            } else {

                Room room = booking.getRoom();
                room.setStatus(true);
                roomRepository.save(room); // Turn on status of room

                RoomType roomType = roomTypeRepository.getRoomTypeById(booking.getRoomTypeId());
                roomType.setDefaultBookingRoom(roomType.getDefaultBookingRoom() + 1); // Set Default of BookingRoom In
                                                                                      // Room Type
                roomTypeRepository.save(roomType);

                bookingRepository.save(booking);
            }
        }
        return modelMapper.map(booking, BookingObjectResponse.class);
    }
}
