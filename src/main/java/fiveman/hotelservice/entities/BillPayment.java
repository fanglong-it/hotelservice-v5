package fiveman.hotelservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "BillPayment")
public class BillPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double paymentAmount;
    private Date dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    private Bill bill;

}

