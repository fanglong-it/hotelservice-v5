package fiveman.hotelservice.response;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.entities.Hotel;
import fiveman.hotelservice.entities.RoomType;
import fiveman.hotelservice.entities.Service;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class BookingResponse {
    private Booking booking;
    private RoomType roomType;
    private Hotel hotel;
    private Service service;

}
