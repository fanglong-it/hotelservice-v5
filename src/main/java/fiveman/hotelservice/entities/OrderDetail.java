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
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Service service;

    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;

    private int quantity;

    private double price;
    private double amount;

    private int status;

    private String orderDate;
    

}
