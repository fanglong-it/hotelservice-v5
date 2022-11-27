package fiveman.hotelservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_payment")
public class OrderPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double paymentAmount;

    private String dateTime;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = PaymentMethod.class)
    private PaymentMethod paymentMethod;

    // @OneToMany(mappedBy = "orderPayment", targetEntity = Order.class, fetch = FetchType.LAZY)
    // @JsonManagedReference
    // private List<Order> orders;

}

