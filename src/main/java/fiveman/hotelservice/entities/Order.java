package fiveman.hotelservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Booking.class)
    @JsonBackReference
    private Booking booking;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = OrderPayment.class)
    @JsonBackReference
    private OrderPayment orderPayment;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<OrderDetail> orderDetails;
    
    private double totalAmount;

    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;
    private String status;

}
