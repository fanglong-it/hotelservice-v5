package fiveman.hotelservice.response;
import java.util.List;
import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.entities.OrderDetail;
import fiveman.hotelservice.entities.OrderPayment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponse {
    private long id;
    private Booking booking;
    private OrderPayment orderPayment;
    private List<OrderDetail> orderDetails;
    private double totalAmount;
    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;
    private String status;
    // private Customer customer;
    
}
