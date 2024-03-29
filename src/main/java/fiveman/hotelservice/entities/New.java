package fiveman.hotelservice.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "news")
public class New {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String newName;
    private String ticketInformation;
    private String detailInformation;
    private String address;
    private String description;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String status;
    private int numberOfView;
    private String newsType;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hotel.class)
    @JsonBackReference
    private Hotel hotel;


}
