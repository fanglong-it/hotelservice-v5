package fiveman.hotelservice.service;

import fiveman.hotelservice.response.BookingResponse;

import java.util.List;

public interface EmailService {
    void sendMail(List<BookingResponse> list);
}
