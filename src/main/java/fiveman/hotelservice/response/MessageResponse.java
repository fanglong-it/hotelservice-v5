package fiveman.hotelservice.response;

import fiveman.hotelservice.entities.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageResponse {
    private long id;
    private String messageContent;
    private Booking booking;
}
