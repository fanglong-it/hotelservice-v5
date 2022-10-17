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
@Table(name = "RoomPrice")
public class RoomPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double price;
    private String date;
    private int maxBookingRoom;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomType.class)
    private RoomType roomType;
}
