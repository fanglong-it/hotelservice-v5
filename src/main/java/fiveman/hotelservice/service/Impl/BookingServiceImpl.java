package fiveman.hotelservice.service.Impl;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.BookingRepository;
import fiveman.hotelservice.response.BookingResponse;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.BookingService;
import fiveman.hotelservice.utils.Common;

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
    public BookingResponse mapBookingToResponse(Booking booking){
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

        //ModelMap
        BookingResponse bookingResponse = modelMapper.map(booking, BookingResponse.class);
        return bookingResponse;
    }


    @Override
    public BookingResponse getBookingById(long id) {
        if (!bookingRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Not found id =" + id));
        }
        return mapBookingToResponse(bookingRepository.getBookingById(id));
    }

    

    @Override
    public List<BookingResponse> getAllBooking() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingResponse bookingResponse = mapBookingToResponse(booking);
            bookingResponses.add(bookingResponse);
        }
        return bookingResponses;
    }

    @Override
    public List<BookingResponse> saveBooking(Booking booking) {
        if (bookingRepository.existsById(booking.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + booking.getId()));
        }
        bookingRepository.save(booking);
        // return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Success!");
        return getAllBooking();
    }

    @Override
    public List<BookingResponse> updateBooking(Booking booking) {
        if (!bookingRepository.existsById(booking.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id =" + booking.getId()));
        }
        bookingRepository.save(booking);
        // return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
        return getAllBooking();
    }

    @Override
    public List<BookingResponse> deleteBooking(long id) {
        if (!bookingRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Not found id =" + id));
        }
        bookingRepository.deleteById(id);
        // return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
        return getAllBooking();
    }



    @Override
    public List<BookingResponse> getAllBookingByRoomId(long id) {
        // return bookingRepository.getAllBookingsByRoomId(id);

        List<Booking> bookings = bookingRepository.getAllBookingsByRoomId(id);
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingResponse bookingResponse = mapBookingToResponse(booking);
            bookingResponses.add(bookingResponse);
        }
        return bookingResponses;
    }
    

}
