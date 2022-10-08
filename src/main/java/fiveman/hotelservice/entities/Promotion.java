package fiveman.hotelservice.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private String detailInformation;
    private Date startDate;
    private Date endDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Hotel.class)
    private Hotel hotel;

}
