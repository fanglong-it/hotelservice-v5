package fiveman.hotelservice.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(required = true)
    private long id;
    private String startDate;
    private String endDate;
    private int total;
    private String guestName;
    private String phoneNumber;
    private String email;
    private String billCode;
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Room.class)
    private List<Room> rooms = new ArrayList<>();

}
