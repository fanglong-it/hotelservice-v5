package fiveman.hotelservice.entities;

import io.swagger.annotations.ApiModelProperty;
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
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(required = true)
    private long id;
    private String bookingDate;
    private String bookingTime;
    private String arrivalDate;
    private String departureDate;
    private int numAdults;
    private int numChildren;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    private Room room;

}
