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
    private long bookedToday;
    private String revenue;
    private String accumulateRevenue;
    private long canceledToday;
    private String cancelRevenue;
    private String cancelAccumulateRevenue;
    private long actualArriveToday;
    private long actualDepartureToday;
    private long roomBusy;
    private long numOfStay;
    private List<Booking> bookingList;

}
