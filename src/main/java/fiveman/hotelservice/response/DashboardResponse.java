package fiveman.hotelservice.response;

import fiveman.hotelservice.entities.Booking;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class DashboardResponse {
    private String bookedToday;
    private String revenue;
    private String accumulateRevenue;
    private String canceledToday;
    private String cancelRevenue;
    private String cancelAccumulateRevenue;
    private String actualArriveToday;
    private String actualDepartureToday;
    private String roomBusy;
    private String numOfStay;
    private List<Booking> bookingList;

}
