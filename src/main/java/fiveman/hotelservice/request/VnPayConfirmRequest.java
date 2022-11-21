package fiveman.hotelservice.request;

import fiveman.hotelservice.entities.Customer;
import fiveman.hotelservice.entities.Hotel;
import fiveman.hotelservice.entities.SpecialUtility;
import fiveman.hotelservice.entities.Utilities;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VnPayConfirmRequest {
    private String vnp_Amount;
    private Customer customer;
    private List<NumberPersonInRoomInfo> persons;
    private RequestServiceBooking requestServiceBooking;
    private List<RoomTypeInfo> roomTypes;
    private SpecialUtilities specialUtilities;
    private BookingDate bookingDates;
    private long hotel_id;
    private List<SpecialUtility> utilities;

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NumberPersonInRoomInfo{
        private int adult;
        private int child;
    }

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookingDate{
        private String startDate;
        private String EndDate;
    }

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestServiceBooking{
        private long id;
        private String check;
    }

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoomTypeInfo{
        private long id;
        private String name;
        private double price;
        private boolean isSelected;
    }

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpecialUtilities{
        private String description;
    }

}




