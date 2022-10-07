package fiveman.hotelservice.entities;

import io.swagger.annotations.ApiModelProperty;
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
@Table(name = "booking")
public class Booking {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @ApiModelProperty(required = true)
//    private long id;
//    private String bookingDate;
//    private String bookingTime;
//    private String arrivalDate;
//    private String departureDate;
//    private int numAdults;
//    private int numChildren;
//
//    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
//    private Room room;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String arrivalDate;
    private String departureDate;
    private String actualArrivalDate;
    private String actualDepartureDate;
    private int numOfAdults;
    private int numOfChildren;
    private int totalAmount;
    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Hotel.class)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    private Room room;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Customer.class)
    private List<Customer> customers;



}
