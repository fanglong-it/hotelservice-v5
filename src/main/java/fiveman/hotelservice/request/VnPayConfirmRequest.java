package fiveman.hotelservice.request;

import fiveman.hotelservice.entities.Customer;
import fiveman.hotelservice.entities.SpecialUtility;
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
    private ServiceBooking serviceBooking;
    private List<RoomTypeInfo> roomTypes;
    private String bookingNotes;
    private BookingDate bookingDates;
    private long hotel_id;
    private List<SpecialUtility> specialUtilities;
    private long paymentMethod;

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
        private String endDate;
    }

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServiceBooking{
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

}




