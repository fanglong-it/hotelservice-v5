package fiveman.hotelservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(required = true)
    @Column(name = "id")
    private long id;

    private int confirmationNo;
    private String arrivalDate;
    private String actualArrivalDate;
    private String departureDate;
    private String actualDepartureDate;
    private int numOfAdult;
    private int numOfChildren;
    private double totalAmount;
    private String roomPayment;
    private String specialNote;
    private long roomTypeId;
    private String status;

    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hotel.class)
    @JsonBackReference
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Room.class)
    private Room room;
    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    private Customer customer;

    @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY, targetEntity = RequestService.class)
    @JsonManagedReference
    private List<RequestService> requestServices;

    @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Order> orders;
}
