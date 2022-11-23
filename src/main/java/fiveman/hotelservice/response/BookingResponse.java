package fiveman.hotelservice.response;

import fiveman.hotelservice.entities.*;
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
