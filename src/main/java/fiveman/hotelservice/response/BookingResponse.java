package fiveman.hotelservice.response;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.entities.Hotel;
import fiveman.hotelservice.entities.RoomType;
import fiveman.hotelservice.entities.Service;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingResponse {
    private Booking booking;
    private RoomType roomType;
    private Hotel hotel;
    private Service service;
    private BookingFailureRoom bookingFailureRoom;

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookingFailureRoom{
        private String bookingFailureRoomName;
        private String startDate;
        private String endDate;
        private int adult;
        private int child;
    }
}
