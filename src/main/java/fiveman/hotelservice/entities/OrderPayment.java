package fiveman.hotelservice.entities;

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
@Table(name = "order_payment")
public class OrderPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double paymentAmount;

    private String dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private PaymentMethod paymentMethod;

    // @ManyToOne(fetch = FetchType.EAGER)
    // private Order order;


}

