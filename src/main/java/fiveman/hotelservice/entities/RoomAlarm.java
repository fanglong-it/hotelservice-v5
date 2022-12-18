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
@Table(name = "RoomAlarm")
public class RoomAlarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String dateTime;

    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Booking.class)
    @JsonBackReference
    private Booking booking;
}
