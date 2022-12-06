package fiveman.hotelservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    private double longtitude;
    private double latidute; 

    private String openTime;
    private String closeTime;
    private String address;
    private String description;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hotel.class)
    @JsonBackReference
    private Hotel hotel;

}
