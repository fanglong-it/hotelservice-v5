package fiveman.hotelservice.response;

import lombok.*;

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
}
