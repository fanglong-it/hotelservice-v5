package fiveman.hotelservice.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Service.class)
    // @JsonBackReference
    private Service service;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
    @JsonBackReference
    private Order order;

    private int quantity;

    private double price;
    private double amount;

    private String orderDate;
    

}
