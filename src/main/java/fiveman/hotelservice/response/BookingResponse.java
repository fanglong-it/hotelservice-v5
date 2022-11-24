package fiveman.hotelservice.response;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.entities.Hotel;
import fiveman.hotelservice.entities.RoomType;
import fiveman.hotelservice.entities.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingResponse {
    private Booking booking;
    private RoomType roomType;
    private Hotel hotel;
    private Service service;
}
