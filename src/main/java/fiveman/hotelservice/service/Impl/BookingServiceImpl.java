package fiveman.hotelservice.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.entities.Customer;
import fiveman.hotelservice.entities.CustomerBooking;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.BookingRepository;
import fiveman.hotelservice.repository.CustomerBookingRepository;
import fiveman.hotelservice.repository.CustomerRepository;
import fiveman.hotelservice.repository.HotelRepository;
import fiveman.hotelservice.repository.RoomRepository;
import fiveman.hotelservice.request.BookingRequest;
import fiveman.hotelservice.request.CheckInRequest;
import fiveman.hotelservice.response.BookingObjectResponse;
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
    public CheckInRequest checkInBooking(CheckInRequest checkInRequest) {

        BookingRequest bookingRequest = checkInRequest.getBookingRequest();
        Booking booking = modelMapper.map(bookingRequest, Booking.class);

        //getCurrent Date time
        String currentDateTime = Utilities.getCurrentDateByFormat("dd/MM/YYYY HH:ss:mm");
        booking.setActualArrivalDate(currentDateTime);
        booking.setUpdateDate(Utilities.getCurrentDateByFormat("dd/MM/YYYY"));
        booking.setStatus("Check In");
        booking.setCustomer(customerRepository.getCustomerById(booking.getCustomer().getId()));
        booking.setHotel(hotelRepository.getHotelById(booking.getHotel().getId()));
        booking.setRoom(roomRepository.getRoomById(booking.getRoom().getId()));
        bookingRepository.save(booking);
        booking = bookingRepository.getBookingById(booking.getId());
        List<Customer> customers = checkInRequest.getCustomer();
        boolean checkOccur = true;
        int occurpancy = booking.getRoom().getRoomType().getMaxOccupancy();
        int roomOccurpancy = customers.size();
        if (occurpancy < roomOccurpancy) {
            checkOccur = false;
        }
        if (checkOccur) {
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
        return checkInRequest;
    }

}
