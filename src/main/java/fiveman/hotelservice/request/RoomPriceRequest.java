package fiveman.hotelservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomPriceRequest {
    private long id;
    private long price;
    private String date;
    private int maxBookingRoom;
    private long roomType_Id;
}
