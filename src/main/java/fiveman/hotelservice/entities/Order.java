package fiveman.hotelservice.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Booking.class)
    @JsonBackReference
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = OrderPayment.class)
    private OrderPayment orderPayment;

    @OneToMany(mappedBy = "order", targetEntity = OrderDetail.class, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;
    
    private double totalAmount;

    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;
    private String status;

}
