package fiveman.hotelservice.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;


import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(required = true)
    private long id;
    private String name;
    private String roomNo;
    private String description;
    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hotel.class)
    @JsonBackReference
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = RoomType.class)
    @JsonBackReference
    private RoomType roomType;

    // @OneToMany(fetch = FetchType.LAZY, targetEntity = Booking.class, mappedBy = "room")
    // @JsonManagedReference
    // private List<Booking> bookings;

}
