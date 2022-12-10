package fiveman.hotelservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = RoomType.class, cascade=CascadeType.ALL)
    @JsonBackReference
    private RoomType roomType;
}
