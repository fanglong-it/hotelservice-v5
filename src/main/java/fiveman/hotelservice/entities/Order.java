package fiveman.hotelservice.entities;

import lombok.*;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


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
    @JsonManagedReference
    private Booking booking;
    
    private double totalAmount;

    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;
    private String status;

    
}
