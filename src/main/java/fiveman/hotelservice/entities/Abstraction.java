package fiveman.hotelservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "abstraction")
public class Abstraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name are mandatory")
    private String name;

    @NotBlank(message = "Abstraction Type are mandatory")
    private String abstractionType;
    private int distance;
    private String address;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Hotel.class)
    private Hotel hotel;

}
