package fiveman.hotelservice.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    private String status;

    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;
    private long roomTypeId;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Hotel.class)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    private Room room;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    private Customer customer;

    @OneToMany(mappedBy = "booking")
    @JsonManagedReference
    private List<RequestService> requestServices;

}
