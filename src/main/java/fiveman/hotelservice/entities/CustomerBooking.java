package fiveman.hotelservice.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "customer_stay_booking")
public class CustomerBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(required = true)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Booking.class)
    private Booking booking;

    private String primaryCustomer;

}
